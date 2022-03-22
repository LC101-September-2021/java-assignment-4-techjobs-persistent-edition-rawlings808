package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }
    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute(newSkill);
            return "skills/add";
        }
        skillRepository.save(newSkill);
        return "redirect:";
    }
    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }
}


//    @PostMapping("add")
//    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
//                                    Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            return "employers/add";
//        }
//        employerRepository.save(newEmployer);
//        return "redirect:";
//    }
//
//    @GetMapping("view/{employerId}")
//    public String displayViewEmployer(Model model, @PathVariable int employerId) {
//
//        Optional optEmployer = employerRepository.findById(employerId);
//        if (optEmployer.isPresent()) {
//            Employer employer = (Employer) optEmployer.get();
//            model.addAttribute("employer", employer);
//            return "employers/view";
//        } else {
//            return "redirect:../";
//        }
//    }
//}