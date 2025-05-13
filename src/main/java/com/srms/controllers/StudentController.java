package com.srms.controllers;

import com.srms.entities.Student;
import com.srms.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired 
    private StudentService studentService;

    @GetMapping("/home")
    public String showStudentHome() {
        return "studenthome"; // Loads studenthome.html
    }

    @GetMapping("/viewResults")
    public String showStudentResults(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "viewResults"; // Loads viewResults.html
    }
}
