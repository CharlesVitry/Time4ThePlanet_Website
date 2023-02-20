package fr.ima.ihm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.ima.ihm.beans.Student;
import fr.ima.ihm.service.EtudiantService;

@Controller
public class CreerEtudiantController {

	@Autowired
	private EtudiantService dao;

	@RequestMapping(
			  value = "/save", 
			  method = RequestMethod.POST)
    public String creerEtudiant(@ModelAttribute("student") Student student) {
        // post request to add a new student
		dao.creer(student);
		return "redirect:/";
    }	
	
	@RequestMapping(
			  value = "/register", 
			  method = RequestMethod.GET)
	public String afficherFormulaireInscription(Model model) {
		model.addAttribute("student", new Student());
		return "inscription";
	}		
}
