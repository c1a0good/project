package se.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.DAO.DoctorsRepository;
import se.DAO.SpecializationsRepository;
import se.pckg.Doctor;
import se.pckg.Specialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Controller
public class DocsController {
    private ArrayList<Doctor> doctors;
    private Collection<Doctor> beans;
    private Specialization specialization;

    @GetMapping("/doctors/{id}")
    public String docShow(@PathVariable("id") Integer id, Model model){
        specialization = SpecializationsRepository.readById(id);
        beans = DoctorsRepository.readAllWithSpec(specialization.getName());
        doctors = new ArrayList<>(beans);
        Collections.sort(doctors);
        model.addAttribute("doctors", doctors);
        model.addAttribute("specialization", specialization);
        return "indexDocs";
    }

    @DeleteMapping("/doctor/{id}")
    public String docDelete(@PathVariable("id") Integer id, Model model){
        SpecializationsRepository.delete(id);
        return "redirect:/doctors/" + specialization.getId();
    }

    @PatchMapping("/doctor/edit/{id}")
    public String docPatch(@PathVariable("id") Integer id, Model model){
        Collection<Specialization> beans = SpecializationsRepository.readAll();
        ArrayList<Specialization>  specs = new ArrayList<>(beans);
        Collections.sort(specs);
        model.addAttribute("specialization", specialization);
        model.addAttribute("specs", specs);
        model.addAttribute("doctor",  doctors.get(id - 1));
        return "editDocs";
    }

    @GetMapping("/doctor/create")
    public String docCreate(Model model){
        Doctor newDoctor = new Doctor();
        Collection<Specialization> beans = SpecializationsRepository.readAll();
        ArrayList<Specialization>  specs = new ArrayList<>(beans);
        Collections.sort(specs);
        model.addAttribute("specialization", specialization);
        model.addAttribute("specs", specs);
        model.addAttribute("doctor", newDoctor);
        return "createDocs";
    }

    @PostMapping("/doctor/save")
    public String docSave(@ModelAttribute("newSpecialization") Doctor doctor, Model model){
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        if(doctor.getId() == -1){
            DoctorsRepository.create(doctor);
        }
        else{
            DoctorsRepository.update(doctor);
        }
        return "redirect:/doctors/" + SpecializationsRepository.readByName(doctor.getSpecialization()).getId();
    }
}