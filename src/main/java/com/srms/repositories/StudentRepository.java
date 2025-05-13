package com.srms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srms.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {}

