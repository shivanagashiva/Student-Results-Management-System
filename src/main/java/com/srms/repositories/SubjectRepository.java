package com.srms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srms.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}

