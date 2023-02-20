package com.example.employeethymeleaf.controller;


import com.example.employeethymeleaf.model.Address;
import com.example.employeethymeleaf.model.Employee;
import com.example.employeethymeleaf.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private ICrudService<Employee> iCrudService;
    @Autowired
    private ICrudService<Address> iCrudService1;


    @GetMapping
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("listEmployees");
        modelAndView.addObject("employees", iCrudService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("addressList",iCrudService1.findAll());
        return "/createEmployee";
    }

    @PostMapping("/createEmp")
    public ModelAndView createEmployee(@ModelAttribute Employee employee) {
        iCrudService.save(employee);
        ModelAndView modelAndView = new ModelAndView("listEmployees");
        modelAndView.addObject("employees", iCrudService.findAll());
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        model.addAttribute("employee", iCrudService.findById(id));
        model.addAttribute("addressList",iCrudService1.findAll());
        return "/update";
    }

    @PostMapping("/update")
    public ModelAndView updateEmployee(@ModelAttribute Employee employee) {
        iCrudService.save(employee);
        ModelAndView modelAndView = new ModelAndView("listEmployees");
        modelAndView.addObject("employees", iCrudService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        iCrudService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("listEmployees");
        modelAndView.addObject("employees", iCrudService.findAll());
        return modelAndView;
    }

}