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

    // @RequestMapping("/calcuconvert")
    // public String calcuconvert(@RequestParam(value = "bilPertama", required = false) String bilPertama, @RequestParam(value = "bilKedua", required = false) String bilKedua, Model model){
    //     int hasil = Integer.parseInt(bilPertama) + Integer.parseInt(bilKedua);
    //     model.addAttribute("bilPertama", bilPertama);
    //     model.addAttribute("bilKedua", bilKedua);
    //     model.addAttribute("hasil", hasil);
    //     return "calcuconvert";
    // }

    @RequestMapping("/calcuconvert")
    public String calcuconvert(@RequestParam(value = "bilPertama", required = false) String bilPertama, @RequestParam(value = "bilKedua", required = false) String bilKedua, Model model){
        int hasil = Integer.parseInt(bilPertama) + Integer.parseInt(bilKedua);
        model.addAttribute("bilPertama", bilPertama);
        model.addAttribute("bilKedua", bilKedua);
        model.addAttribute("hasil", hasil);

        String[] numbers = { "", " Satu", " Dua", " Tiga", " Empat", " Lima", " Enam", " Tujuh", " Delapan", " Sembilan", " Sepuluh" };
        String[] dozens = { "", " Belas", " Dua Puluh", " Tiga Puluh", " Empat Puluh", " Lima Puluh", " Enam Puluh", " Tujuh Puluh", " Delapan Puluh", " Sembilan Puluh" };
        String[] hundreds= { "", " Seratus", " Dua Ratus", " Tiga Ratus", " Empat Ratus", " Lima Ratus", " Enam Ratus", " Tujuh Ratus", " Delapan Ratus", " Sembilan Ratus" };
        String[] thousands = { "", " Seribu", " Dua Ribu", " Tiga Ribu", " Empat Ribu", " Lima Ribu", " Enam Ribu", " Tujuh Ribu", " Delapan Ribu", " Sembilan Ribu" };

        if (hasil < 9999 && hasil > 0) {
            int satuan, puluhan, ratusan, ribuan;
            satuan= hasil % 10;
            puluhan= (hasil % 100) / 10;
            ratusan= (hasil % 1000) / 100;
            ribuan= hasil / 1000;
            if (puluhan== 1) {
                if (satuan == 1) {
                    model.addAttribute("bilangan", thousands[ribuan] + hundreds[ratusan] + "Se" + dozens[puluhan]);
                } else {
                    model.addAttribute("bilangan", thousands[ribuan] + hundreds[ratusan] + numbers[satuan] + dozens[puluhan]);
                } 
            } else {
                model.addAttribute("bilangan", thousands[ribuan] + hundreds[ratusan] + dozens[puluhan] + numbers[satuan]);
            }
        } else {
            model.addAttribute("bilangan", "Converting failed");
        }

        return "calcuconvert";
    }
}