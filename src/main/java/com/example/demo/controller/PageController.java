package com.example.demo.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController{
    @RequestMapping("/hello")
    public String index(){
        return "hello";
    }

    // @RequestMapping("/hello2")
    // public String hello2(@RequestParam(value = "name") String name, Model model){
    //     model.addAttribute("name", name);
    //     return "hello2";
    // }

    @RequestMapping(value={"/hello2", "/hello2/{name}"})
    public String helloPath(@PathVariable Optional<String> name, Model model){
        if(name.isPresent()){
            model.addAttribute("name", name.get());
        }else{
            model.addAttribute("name", "Phoenix");
        }
        return "hello2";
    }

    // @RequestMapping(value={"/hello2", "/hello2/{name}"})
    // public String helloPath(@PathVariable String name, Model model){
    //     model.addAttribute("name", name);
    //     return "hello2";
    // }

    @RequestMapping("/calcuconvert")
    public String calcuconvert(@RequestParam(value = "bilPertama", required = false) String bilPertama, @RequestParam(value = "bilKedua", required = false) String bilKedua, Model model){
        int hasil = Integer.parseInt(bilPertama) + Integer.parseInt(bilKedua);
        model.addAttribute("bilPertama", bilPertama);
        model.addAttribute("bilKedua", bilKedua);
        model.addAttribute("hasil", hasil);
        return "calcuconvert";
    }
}