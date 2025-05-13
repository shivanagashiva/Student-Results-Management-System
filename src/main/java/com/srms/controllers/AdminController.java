package com.srms.controllers;

import com.srms.entities.Student;
import com.srms.entities.Subject;
import com.srms.services.StudentService;
import com.srms.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired 
    private StudentService studentService;

    @Autowired 
    private SubjectService subjectService;

    @GetMapping("/home")
    public String showAdminHome() {
        return "adminhome";  // Loads adminhome.html
    }

    @GetMapping("/manageStudents")
    public String showStudentManagementPage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "manageStudents"; // Loads manageStudents.html
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam String name) {
        Student student = new Student();
        student.setName(name);
        studentService.addStudent(student);
        return "redirect:/admin/manageStudents";
    }

    @GetMapping("/manageSubjects")
    public String showManageSubjectsPage(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("subjects", subjects);
        model.addAttribute("students", students);
        return "manageSubjects"; // Loads manageSubjects.html
    }

    @PostMapping("/addSubject")
    public String addSubject(@RequestParam Long studentId, @RequestParam String subjectName, @RequestParam int marks) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            Subject subject = new Subject();
            subject.setName(subjectName);
            subject.setMarks(marks);
            subject.setStudent(student);
            subjectService.addSubject(subject);
        } else {
            System.out.println("Error: Student not found with ID " + studentId);
        }
        return "redirect:/admin/manageStudents";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long id) {
        if (studentService.getStudentById(id) != null) {
            studentService.deleteStudent(id);
        } else {
            System.out.println("Error: Student not found with ID " + id);
        }
        return "redirect:/admin/manageStudents";
    }

    @GetMapping("/deleteSubject")
    public String deleteSubject(@RequestParam Long id) {
        if (subjectService.getSubjectById(id) != null) {
            subjectService.deleteSubject(id);
        } else {
            System.out.println("Error: Subject not found with ID " + id);
        }
        return "redirect:/admin/manageSubjects";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam Long id, @RequestParam String name) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            studentService.updateStudent(id, name);
        } else {
            System.out.println("Error: Student not found with ID " + id);
        }
        return "redirect:/admin/manageStudents";
    }
}
