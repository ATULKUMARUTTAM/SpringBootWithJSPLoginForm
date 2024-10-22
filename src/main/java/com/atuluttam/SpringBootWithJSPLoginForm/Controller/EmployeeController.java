package com.atuluttam.SpringBootWithJSPLoginForm.Controller;

import com.atuluttam.SpringBootWithJSPLoginForm.Model.Employee;
import com.atuluttam.SpringBootWithJSPLoginForm.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // Display login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // JSP page name
    }

    // Handle login logic
    @PostMapping("/login")
    public String login(@RequestParam("username") String name, @RequestParam("password") String password, Model model) {
        Employee e = employeeRepository.findByName(name);

        if (e != null) {
            if (e.getPassword().equals(password)) {
                model.addAttribute("name", name);
                model.addAttribute("password", password);
                return "success"; // Redirect to success.jsp
            } else {
                // If the password does not match, redirect to error page
                model.addAttribute("name", name);
                return "error"; // Redirect to error.jsp
            }
        } else {
            // If user does not exist, redirect to register page with a message
            model.addAttribute("message", "User does not exist. Please register.");
            model.addAttribute("username", name); // Keep the entered username to avoid re-entering
            return "register"; // Display the register page
        }
    }

    // Display register page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        // Ensure message and username are set to avoid null values on the form
        model.addAttribute("message", "");
        model.addAttribute("username", "");
        return "register"; // JSP page name
    }

    // Handle registration logic
    @PostMapping("/register")
    public String register(@RequestParam("username") String name, @RequestParam("password") String password, Model model) {
        // Check if the username already exists in the repository
        Employee existingEmployee = employeeRepository.findByName(name);
        if (existingEmployee != null) {
            // If the username exists, ask the user to choose another
            model.addAttribute("message", "Username already exists. Please choose another username.");
            model.addAttribute("username", name);
            return "register"; // Stay on the registration page with an error message
        }

        // If username is unique, save the new employee
        Employee e = new Employee();
        e.setName(name);
        e.setPassword(password);
        employeeRepository.save(e);

        model.addAttribute("name", name);
        model.addAttribute("password", password);
        return "registersuccess"; // Redirect to registersuccess.jsp
    }
}