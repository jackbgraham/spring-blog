package com.codeup.springblog.controllers;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts() {
        return "this is the posts page";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postID(@PathVariable String id) {
        return  "this is the page for " + id;
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return  "this is the placeholder for a post creation form";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postID() {
        return  "this is the placeholder for a post creation form";
    }

}
