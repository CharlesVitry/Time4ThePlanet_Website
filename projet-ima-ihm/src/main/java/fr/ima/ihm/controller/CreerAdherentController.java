package fr.ima.ihm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.ima.ihm.beans.Adherents;
import fr.ima.ihm.service.AdherentService;

@Controller
public class CreerAdherentController {

	@Autowired
	private AdherentService dao;

	@RequestMapping(
			  value = "/save", 
			  method = RequestMethod.POST)
    public String creerAdherent(@ModelAttribute("adherent") Adherents adherents) {
		dao.creer(adherents);
		
        return "redirect:/list";
    }	
	
	@RequestMapping(
			  value = "/register", 
			  method = RequestMethod.GET)
	public String afficherFormulaireInscription(Model model) {
		model.addAttribute("adherents", new Adherents());
		

		
		return "inscription";
		
	}		
}
