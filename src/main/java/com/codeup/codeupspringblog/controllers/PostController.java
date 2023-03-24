package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String postIndexPage() {
        return "This is the posts index page!";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postIndexPageId(@PathVariable int id) {
        return "This is the view for the individual posts page! Your id is " + id;
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
