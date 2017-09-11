package byAJ.controller;

import byAJ.models.BullhornPost;
import byAJ.models.BullhornUser;
import byAJ.repositories.BullhornPostRepository;
import byAJ.repositories.BullhornUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    BullhornPostRepository postRepository;

    @Autowired
    BullhornUserRepository userRepository;

    @GetMapping("/addpost")
    public String loadPostForm(Model model){
        model.addAttribute("post", new BullhornPost());
        return "addpost";
    }

    @GetMapping("/repost/{postid}")
    public String loadRepostForm(@RequestParam("postid") long postid, Model model){
        model.addAttribute("post", new BullhornPost());
        model.addAttribute("postid", postid);
        return "addpost";
    }

    @PostMapping("/addpost")
    public String processPostForm(@ModelAttribute BullhornPost post, Principal principal){
        BullhornUser user = userRepository.findByUsername(principal.getName());
        post.setPostDate(new Date());
        post.setBullhornuser(user);
        postRepository.save(post);
        return "redirect:/";
    }
}
