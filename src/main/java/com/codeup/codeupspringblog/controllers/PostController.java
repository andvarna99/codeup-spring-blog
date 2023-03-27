package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<Post>posts = new ArrayList<>();
        posts.add(new Post(1,"title 1","body of post 1"));
        posts.add(new Post(2,"title 2","body of post 2"));
        model.addAttribute("posts",posts);
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
    @ResponseBody
    public String postIndexPageCreate() {

        return "This is the view for the form for the create a post page!";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String postIndexPageCreatePost() {
        return "This is where we will create a post/!";
    }

}
