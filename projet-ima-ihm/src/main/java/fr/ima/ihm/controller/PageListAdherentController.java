package fr.ima.ihm.controller;

import fr.ima.ihm.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.ima.ihm.service.AdherentService;


@Controller // Balise Controller qui déclare la classe comme permettant de gérer des requêtes HTTP
public class PageListAdherentController {
	
	@Autowired
	private AdherentService dao_adherent; // Appelle à l'interface du service "Etudiant" : lister, créer, MAJ, etc....

    @Autowired
    private PartsService dao_parts;

    @GetMapping("/list") // Annotation qui permet de déclencher la méthode "/list" suite au clic sur un lien
    public String afficherListeDesAdherents(Model model) { // objet model qui sera injecté dans la page de résultat
        model.addAttribute("adherents", dao_adherent.lister()); // Insertion de la liste (résultat de la requete) dans l'objet model
        model.addAttribute("somme", dao_parts.sum_nombre());
        return "listeAdherents"; // page de résultat
    }

}
