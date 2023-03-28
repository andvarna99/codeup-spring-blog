package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@Controller
class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String postIndexPage(Model model) {
        List<Post>posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postIndexPageId(@PathVariable long id, Model model) {
        Post post = postDao.findById(id).get();

        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String postIndexPageCreate() {
        return "create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String postIndexPageCreatePost(@RequestParam String title, @RequestParam String body) {
        Post post = new Post();

        post.setTitle(title);
        post.setBody(body);

        System.out.println(post);
        postDao.save(post);
        return "redirect:/posts";
    }

}
