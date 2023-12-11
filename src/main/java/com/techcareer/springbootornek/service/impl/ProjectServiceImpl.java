package com.techcareer.springbootornek.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techcareer.springbootornek.entity.Project;
import com.techcareer.springbootornek.repository.ProjectRepository;
import com.techcareer.springbootornek.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	
	private final ProjectRepository projectRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public List<Project> getAll() {
		List<Project> projectList= projectRepository.findAll();
		return projectList;
	}

	@Override
	public Project getById(Long id) {
		Project project=projectRepository.findById(id).orElseThrow();
		return project;
	}

	@Override
	public Project save(Project project) {
		projectRepository.save(project);
		return project;
	}

	@Override
	public Project update(Project project) {
		// TODO Auto-generated method stub
		Project projectOld=projectRepository.findById(project.getId()).orElseThrow();
		if (projectOld==null) {
			throw new IllegalArgumentException("Belirtilen ID'ye sahip bir proje bulunamadı.");
		}
		projectOld.setId(project.getId());
		projectOld.setInsertDate(new Date());
		projectOld.setProjectCode(project.getProjectCode());
		projectOld.setProjectName(project.getProjectName());
		Project projectUpd=projectRepository.save(projectOld);
		return projectUpd;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		Project project =projectRepository.findById(id).orElseThrow();
		if(project!=null) {
			projectRepository.deleteById(id);
			return true;
		}
		else {
			throw new IllegalArgumentException("Belirtilen ID'ye sahip bir proje bulunamadı.");
		}
	}

}
