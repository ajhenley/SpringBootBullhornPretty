package byAJ.controller;

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

import javax.validation.Valid;
import java.security.Principal;

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

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postRepository.findAll());
        return "i_index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new BullhornUser());
        return "registration";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") BullhornUser user, BindingResult result, Model model){

        model.addAttribute("user", user);
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index";
    }

    @RequestMapping("/showprofile/{id}")
    public String showProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userRepository.findOne(id));
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
        return "profile";
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }


}