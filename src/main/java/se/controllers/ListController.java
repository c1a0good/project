package se.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.DAO.SpecializationsRepository;
import se.pckg.Specialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Controller
public class ListController {
    private ArrayList<Specialization> specializations;
    private Collection<Specialization> beans;

    @GetMapping()
    public String specShow(Model model){
        beans = SpecializationsRepository.readAll();
        specializations = new ArrayList<>(beans);
        Collections.sort(specializations);
        model.addAttribute("specializations", specializations);
        return "index";
    }

    @DeleteMapping("/specializations/{id}")
    public String specDelete(@PathVariable("id") Integer id, Model model){
        SpecializationsRepository.delete(id);
        return "redirect:/";
    }

    @PatchMapping("/specializations/{id}")
    public String specPatch(@PathVariable("id") Integer id, Model model){
        model.addAttribute("specialization",  specializations.get(id - 1));
        return "edit";
    }

    @GetMapping("/specializations/create")
    public String specCreate(Model model){
        Specialization newSpecialization = new Specialization();
        model.addAttribute("newSpecialization", newSpecialization);
        return "create";
    }

    @PostMapping("/specializations/save")
    public String specSave(@ModelAttribute("newSpecialization") Specialization specialization, Model model){
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        if(specialization.getId() == -1){
            SpecializationsRepository.create(specialization);
        }
        else{
            SpecializationsRepository.update(specialization);
        }
        return "redirect:/";
    }
}
