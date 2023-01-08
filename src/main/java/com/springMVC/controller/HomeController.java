package com.springMVC.controller;

import com.springMVC.model.Programmer;
import com.springMVC.repository.ProgrammerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    ProgrammerRepo pr;

//    @ModelAttribute
//    public void welcome(Model m){
//        m.addAttribute("msg", "Welcome to spring boot Tutorial");
//    }
    @RequestMapping("/")
    public String homePage(){
        return "HomePage";
    }

//    @RequestMapping("/addProgrammer")
//    public String addProgrammer(
//            @RequestParam int pId,
//            @RequestParam String pName,
//            @RequestParam String pLang,
//            ModelMap model){
//        model.addAttribute("pId", pId);
//        model.addAttribute("pName", pName);
//        model.addAttribute("pLang", pLang);
//        return "ProgrammerInfo";
//    }

//    @RequestMapping("/addProgrammer")
//    public ModelAndView addProgrammer(
//            @RequestParam("pId") int i,
//            @RequestParam("pName") String n,
//            @RequestParam String pLang){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("ProgrammerInfo");
//        mv.addObject("pId", i);
//        mv.addObject("pName", n);
//        mv.addObject("pLang", pLang);
//        return mv;
//    }

//    @RequestMapping("/addProgrammer")
//    public ModelAndView addProgrammer(@ModelAttribute Programmer programmer){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("ProgrammerInfo");
//        return mv;
//    }

    //@RequestMapping(value = "/addProgrammer", method = RequestMethod.POST)
    @PostMapping("/addProgrammer")
    public String addProgrammer(@ModelAttribute("p") Programmer programmer){
        pr.save(programmer);
        return "redirect:/allProgrammer";
    }

    @GetMapping("/allProgrammer")
    public String allProgrammer(Model model){
        List<Programmer> p = pr.findAll();

        model.addAttribute("programmers", p);
        return "AllProgrammer";
    }

    @GetMapping("/edit/{id}")
    public String updateProgrammer(@PathVariable("id") Integer id, Model m){
        Programmer programmer = pr.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Programmer Id: "+ id));
        m.addAttribute("programmer", programmer);
        return "UpdateProgrammer";
    }

    @PostMapping("/update/{id}")
    public String updateProgrammer(@PathVariable Integer id, @Validated Programmer programmer, BindingResult result){
        if(result.hasErrors()){
            programmer.setpId(id);
            return "UpdateProgrammer";
        }
        pr.save(programmer);
        return "redirect:/allProgrammer";
    }

    @GetMapping("/delete/{id}")
    public String deleteProgrammer(@PathVariable Integer id){
        pr.deleteById(id);
        return "redirect:/allProgrammer";
    }

    @PostMapping("/searchProgrammer")
    public String allProgrammers(@RequestParam String pLang, Model model){
        List<Programmer> p = pr.findBypLang(pLang);

        model.addAttribute("programmers", p);
        model.addAttribute("isSearchPage", "true");
        model.addAttribute("searchValue", pLang);
        return "AllProgrammer";
    }

}
