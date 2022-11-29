package com.codeup.springblog.controllers;

import com.codeup.weywotspringblog.models.Post;
import com.codeup.weywotspringblog.models.User;
import com.codeup.weywotspringblog.repositories.PostsRepository;
import com.codeup.weywotspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController{

    private final PostsRepository postsDao;
    private final UserRepository usersDao;
    public PostController(PostsRepository postsDao, UserRepository usersDao) {
    this.postsDao =postsDao;
    this.usersDao =usersDao;
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
        return  "/posts/show";
    }

    @GetMapping("/create")
    public String createPost() {
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body){
        User user = usersDao.findById(2L);
        Post post = new Post(title, body, user);
        postsDao.save(post);
        return  "redirect:/posts";
    }

}
