package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
class HomeController {

    private final PostRepository postDao;

    public HomeController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/")
    public String landingPage(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }
}
