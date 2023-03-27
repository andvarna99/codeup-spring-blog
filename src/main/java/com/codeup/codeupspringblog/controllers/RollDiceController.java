package com.codeup.codeupspringblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/roll-dice")
public class RollDiceController {

    @GetMapping
    public String guessNum(){
        return "roll_dice";
    }

    @GetMapping("/{userGuess}")
    public String userGuess(Model model, @PathVariable int userGuess){
        int diceRoll = (int) (Math.random() * 6) + 1;
        model.addAttribute("diceRoll",diceRoll);
        model.addAttribute("userGuess", userGuess);

//        ~walkthrough~
//        String winMessage = "Sorry, you lose!";
//        if(userGuess == diceRoll){
//            winMessage = "You won!";
//        }
//        model.addAttribute("winMsg", winMessage);
//        List <Integer> rolls = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            rolls.add(Random.nextInt(6)+1);
//        }

        return "roll_dice";
    }

}
