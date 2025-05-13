package com.srms.services;

import com.srms.entities.Subject;
import com.srms.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired 
    private SubjectRepository subjectRepository;

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    // ✅ Get all subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // ✅ Get subject by ID
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    // ✅ Delete subject by ID
    public void deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        }
    }

    // ✅ Update marks for a subject
    public void updateSubjectMarks(Long id, int newMarks) {
        Optional<Subject> subjectOpt = subjectRepository.findById(id);
        if (subjectOpt.isPresent()) {
            Subject subject = subjectOpt.get();
            subject.setMarks(newMarks);
            subjectRepository.save(subject);
        }
    }
}
