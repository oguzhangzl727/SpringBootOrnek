
package com.techcareer.springbootornek.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.techcareer.springbootornek.entity.Project;
import com.techcareer.springbootornek.service.impl.ProjectServiceImpl;

@Controller
public class ProjectController {
	
	private final ProjectServiceImpl projectServiceImpl;
	
	
	public ProjectController(ProjectServiceImpl projectServiceImpl)
	{
	this.projectServiceImpl = projectServiceImpl;
	}
	
	//@RequestMapping(value="/allprojects",method=RequestMethod.GET)
	//public ResponseEntity<List<Project>> getData() {
		//List<Project> liste = projectServiceImpl.getAll();
		
		//return new ResponseEntity<>(liste,HttpStatus.OK);
		//return ResponseEntity.ok(liste);
	//}
	
	@RequestMapping(value="/allprojects",method=RequestMethod.GET)
	public ModelAndView getData() {
		List<Project> liste = projectServiceImpl.getAll();
		ModelAndView model = new ModelAndView("projects");
		model.addObject("projeListesi",liste);
		return model;
	}
	
	@RequestMapping(value="/editproject/{id}")
	public String editProje(@PathVariable Long id, Model m) {
		Project projectDetay = projectServiceImpl.getById(id);
		m.addAttribute("command",projectDetay);
		return "editproject";
	}
	
	
	@RequestMapping("/newproject")
	public ModelAndView showForm() {
		
		return new ModelAndView("newproject","command",new Project());
	}
	
	@RequestMapping(value="/addNewProject",method=RequestMethod.POST)
	public ModelAndView kaydet(@ModelAttribute("project")Project project,
			BindingResult result, ModelMap model)
	{
		Date date = new Date();
		project.setInsertDate(date);
		projectServiceImpl.save(project);
		return new ModelAndView("redirect:allprojects");
	}
	
	
	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public String editSave(@ModelAttribute("project") Project project) {
	    projectServiceImpl.update(project);

	    return "redirect:/allprojects";
	}
	
	
	@RequestMapping(value = "/deleteproject/{id}", method = RequestMethod.GET)
	public String deleteProject(@PathVariable Long id) {
	    projectServiceImpl.delete(id);
	    
	    return "redirect:/allprojects";
	}
	
	 
}