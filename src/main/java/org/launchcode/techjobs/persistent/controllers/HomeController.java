package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */


@Controller
public class HomeController {

    // MyToDo
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");

        Sort sort = Sort.by(Sort.Order.asc("name"));
        model.addAttribute("jobs", jobRepository.findAll(sort));

        return "index";
    }

    @GetMapping("add") // DEFAULT
    public String displayAddJobForm(Model model) {

        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());

        // Redundant but needed to pass TestTaskThree
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        Sort sort = Sort.by(Sort.Order.asc("name"));
        model.addAttribute("employers", employerRepository.findAll(sort));
        model.addAttribute("skills", skillRepository.findAll(sort));

        return "add";
    }

    // MyToDo pass skills as a @RequestParam
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model,
                                    @RequestParam int employerId, // Added default value to refresh form
                                    @RequestParam List<Integer> skills) {
        // Note: @RequestParam Integer over @RequestParam int would avoid null errors

        model.addAttribute("title", "Add Job");


        Sort sort = Sort.by(Sort.Order.asc("name"));
        model.addAttribute("employers", employerRepository.findAll(sort));
        model.addAttribute("skills", skillRepository.findAll(sort));


        model.addAttribute("employerError", "⚠\uFE0F Required: Select an employer");
        model.addAttribute("skillError", "⚠\uFE0F Required: Select at least one skill");


        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            newJob.setEmployer(employer);
        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        // MyToDo
        Optional<Job> optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "view";
        }

       // model.addAttribute("jobs", jobRepository.findById(jobId));
        return "redirect";
    }

}