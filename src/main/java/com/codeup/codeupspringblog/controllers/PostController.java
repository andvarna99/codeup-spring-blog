package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Controller
class PostController {
    private final PostRepository postDao;

    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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

//        model.addAttribute("posts", post);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        model.addAttribute("email",post.getUser().getEmail());
        model.addAttribute("id", post.getId());
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String postPageCreate(Model model) {
        model.addAttribute("post",new Post());
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String postPageCreatePost(@ModelAttribute Post post) {
//        Post post = new Post();
//        model.addAttribute("post",post);
        User user = userDao.findById(1L).get();
//
//        post.setTitle(title);
//        post.setBody(body);
        post.setUser(user);

//        System.out.println(post);
        postDao.save(post);
        return "redirect:/posts";
    }

    @RequestMapping(path = "/posts/edit/{id}", method = RequestMethod.GET)
    public String postPageEdit(Model model, @PathVariable long id) {
        model.addAttribute("post",postDao.findById(id));
        return "posts/edit";
    }
}
