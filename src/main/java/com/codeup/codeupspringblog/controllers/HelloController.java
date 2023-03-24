package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

//--States that this is a class:
//--we are telling spring that this is going to be a controller
@Controller
class HelloController {

    //-- This defines a method that should be invoked when a request is received
    //-- drives to the specific url we are headed to
    //-- forward slash indicates it's an index
    @GetMapping("/hello")
    //-- Tells spring that we want to return whatever is in this method in the body of the url response
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{firstname}&{lastname}")
    @ResponseBody
    public String sayHello(@PathVariable String firstname, @PathVariable String lastname){
        return "Hello, " + firstname + " " + lastname + "!";
    }

    @GetMapping("/dice")
    @ResponseBody
    public String rollDice(){
        int diceRoll = (int) (Math.random() * 6) + 1;
        return "roll-result: " + diceRoll;
    }

}
