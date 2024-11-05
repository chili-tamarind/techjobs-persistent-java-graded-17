package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.*;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    // MyToDo
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "employers/add";
        }
        else{
            employerRepository.save(newEmployer); // MyToDo
            //model.addAttribute("employer", newEmployer);
            //model.addAttribute("employerRepository", employerRepository);
        }

        return "redirect:";
    }
    // Error: [Field 'id' doesn't have a default value] [insert into employer (location,name) values (?,?)]

@GetMapping("view/{employerId}")
public String displayViewEmployer(Model model, @PathVariable int employerId) {

    //Optional optEmployer = null;
    Optional optEmployer = employerRepository.findById(employerId);
    if (optEmployer.isPresent()) {
        Employer employer = (Employer) optEmployer.get();
        model.addAttribute("employer", employer);
        return "employers/view";
    } else {
        return "redirect:../";
    }

}

    // MyToDo
    // Add an index method that responds with a list of all employers in the database.
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "All Employers");
        model.addAttribute("employers", employerRepository.findAll());

        return "employers/index";
    }
}


