package com.techcareer.springbootornek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techcareer.springbootornek.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
