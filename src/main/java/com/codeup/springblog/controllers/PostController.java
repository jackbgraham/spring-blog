package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostsRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController{

    private final PostsRepository postsDao;
    private final UserRepository usersDao;
    private final EmailService emailService;
    public PostController(PostsRepository postsDao, UserRepository usersDao, EmailService emailService) {
    this.postsDao =postsDao;
    this.usersDao =usersDao;
    this.emailService = emailService;
}

    @GetMapping
    public String allPosts(Model model){
        List<Post> allPosts = postsDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "/posts/index";
    }
    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@ModelAttribute Post post){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        ).getId();
        if (currentUserId == 0) {
            return "redirect:/login";
        }
        User user = usersDao.findById(currentUserId);
        post.setUser(user);
        postsDao.save(post);
        emailService.prepareAndSend(user, post.getTitle(), post.getBody());
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String showEditPostForm(@PathVariable long id, Model model){
        System.out.println("yeehaw partner");
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@ModelAttribute Post post){
        User user = usersDao.findById(1L);
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable long id) {
        System.out.println("Inside deletePost");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.printf("User: %s%n", user.getUsername());
        Post post = postsDao.findById(id);
        long UserId = user.getId();
        long PostUserId = post.getUser().getId();
        if (UserId == PostUserId) {
            System.out.println("UserId and PostUserId are equal");
            postsDao.delete(post);
        }
        return "redirect:/posts";
    }
//    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
//    public void deletePost(@PathVariable String id) {
//        postService.deletePost(id);
//    }


//    does this go here?
//    @Value("${file-upload-path}")
//    private String uploadPath;

//    @GetMapping("/fileupload")
//    public String fileuploadPost(Model model) {
//        model.addAttribute("post", new Post());
//        return "/posts/fileupload";
//    }
//
//    @PostMapping("/fileupload")
//    public String submitPost(@ModelAttribute Post post) {
//
//    }
}
