package fr.ima.ihm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.ima.ihm.beans.Adherents;
import fr.ima.ihm.service.AdherentService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Controller
public class CreerAdherentController {

	@Autowired
	private AdherentService dao;

	@RequestMapping(
			  value = "/save", 
			  method = RequestMethod.POST)
    public String creerAdherent(@ModelAttribute("adherent") Adherents adherents, Model model) {

		if(isMajeur(adherents.getBirthDate())){
			dao.creer(adherents);
			return "redirect:/list";
		} else {
			model.addAttribute("errorMessage", "Vous devez être majeur pour vous inscrire.");
			return "index";
		}
    }	
	
	@RequestMapping(
			  value = "/index",
			  method = RequestMethod.GET)
	public String afficherFormulaireInscription(Model model) {
		model.addAttribute("adherents", new Adherents());
		

		
		return "index";
		
	}

	// controle de surface
	public static boolean isMajeur(Date date) {
		LocalDate mtn = LocalDate.now();
		Instant instant = date.toInstant();
		LocalDate dateLocal = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate();

		Period age = Period.between(dateLocal, mtn);
		if (age.getYears() >= 18) {
			return true;
		} else {
			return false;
		}

	}

}
