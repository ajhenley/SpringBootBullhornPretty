package byAJ.controller;

import byAJ.config.CloudinaryConfig;
import byAJ.models.BullhornFollow;
import byAJ.models.BullhornPost;
import byAJ.models.BullhornUser;
import byAJ.repositories.BullhornFollowRepository;
import byAJ.repositories.BullhornPostRepository;
import byAJ.repositories.BullhornUserRepository;
import byAJ.service.UserService;
import byAJ.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;

import com.cloudinary.Singleton;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    BullhornPostRepository postRepository;

    @Autowired
    BullhornUserRepository userRepository;

    @Autowired
    BullhornFollowRepository followRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String index(Model model, Principal principal){
        model.addAttribute("posts", postRepository.findAllOrdered());
        if (principal == null){
            return "i_index";
        } else {
            model.addAttribute("user",userRepository.findByUsername(principal.getName()));
            model.addAttribute("post", new BullhornPost());
            return "s_index";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model, Principal principal){
        if (principal == null){
            model.addAttribute("user", new BullhornUser());
            return "registration";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") BullhornUser user, BindingResult result,
                                          Model model, Principal principal){

        if (principal == null) {
            model.addAttribute("user", user);
            userValidator.validate(user, result);

            if (result.hasErrors()) {
                return "registration";
            } else {
                user.setJoindate(new Date());
                userService.saveUser(user);
                model.addAttribute("message", "User Account Successfully Created");
            }
            return "i_index";
        } else  {
            return "redirect:/";
        }
    }

    @RequestMapping("/showprofile/{id}")
    public String showProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userRepository.findOne(id));
        model.addAttribute("isMe", false);
        return "profile";
    }

    @RequestMapping("/follow/{id}")
    public String followUser(@PathVariable("id") Long id, Model model, Principal principal){
        Long userid = userRepository.findByUsername(principal.getName()).getId();
        followRepository.save(new BullhornFollow(userid, id));
        return "redirect:/myprofile";
    }

    @RequestMapping("/myprofile")
    public String showMyProfile(Principal principal, Model model){
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        model.addAttribute("post", new BullhornPost());
        model.addAttribute("isMe", true);
        return "profile";
    }

    @PostMapping("/updateprofile")
    public String updateProfile(@RequestParam("headshotz") MultipartFile headshot,
                                @RequestParam("backgroundz") MultipartFile background,
            @Valid BullhornUser user, BindingResult result, Model model)
    {
        if(result.hasErrors()){
            return "redirect:/myprofile";
        } else {
            if(!headshot.isEmpty())
            {
                try {
                    Map uploadResult = cloudc.upload(headshot.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                    user.setHeadshot(cloudc.createUrl(
                            uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString(),
                            64,
                            64,
                            "fill"));
                    user.setProfilepic(cloudc.createUrl(
                            uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString(),
                            127,
                            127,
                            "fill"));
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("message", "Sorry I can't upload that headshot!");
                    return "redirect:/myprofile";
                }

                try {
                    Map uploadResult = cloudc.upload(background.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                    user.setBackground(cloudc.createUrl(
                            uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString(),
                            1500,
                            300,
                            "fill"));
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("message", "Sorry I can't upload that background!");
                    return "redirect:/myprofile";
                }
            }
            userRepository.save(user);
            return "redirect:/myprofile";
        }
    }



    public UserValidator getUserValidator() {
        return userValidator;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }


}