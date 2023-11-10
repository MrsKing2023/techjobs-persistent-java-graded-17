package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;


    @GetMapping ("/")
    public String index(Model model) {
        model.addAttribute("view", "All Skills");
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skill/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "skill/add";
        }
        skillRepository.save(newSkill);
        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional optSkill = Optional.empty();
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            Optional<Skill> result = skillRepository.findById(skillId);
            if (result.isEmpty()){
                model.addAttribute("skill", "Invalid Skill ID" + skillId);
            } else {
                Skill skill = result.get();
                model.addAttribute("skill","All Skill: " + skill.getName());
                // model.addAttribute("skills", skill.getSkill());
            }
        }
        return "redirect:../";

    }

}