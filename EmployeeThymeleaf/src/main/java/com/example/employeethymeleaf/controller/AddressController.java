package com.example.employeethymeleaf.controller;


import com.example.employeethymeleaf.model.Address;
import com.example.employeethymeleaf.model.Employee;
import com.example.employeethymeleaf.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("address")
public class AddressController {
    @Autowired
    public ICrudService<Address> iCrudService;
    @Autowired
    public ICrudService<Employee> iCrudService1;
   @GetMapping("/createForm")
    public String crateAddressForm(Model model){
       model.addAttribute("address",new Address());
       return "createAddressForm";

   }
   @PostMapping("/createAddress")
    public ModelAndView createAddress(@ModelAttribute Address address){
       iCrudService.save(address);
       ModelAndView modelAndView = new ModelAndView("listEmployees");
       modelAndView.addObject("employees", iCrudService1.findAll());
       return modelAndView;
   }

}
