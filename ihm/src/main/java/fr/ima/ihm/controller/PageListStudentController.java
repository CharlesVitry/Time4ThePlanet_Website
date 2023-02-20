package fr.ima.ihm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.ima.ihm.service.EtudiantService;


@Controller // Balise Controller qui déclare la classe comme permettant de gérer des requêtes HTTP
public class PageListStudentController {
	
	@Autowired
	private EtudiantService dao; // Appelle à l'interface du service "Etudiant" : lister, créer, MAJ, etc.... 

    @GetMapping("/list") // Annotation qui permet de déclencher la méthode "/list" suite au clic sur un lien
    public String afficherListeDesEtudiants(Model model) { // objet model qui sera injecté dans la page de résultat
        model.addAttribute("students", dao.lister()); // Insertion de la liste (résultat de la requete) dans l'objet model
        return "listeDesEtudiants"; // page de résultat
    }

}
