package fr.ima.ihm.controller;

import fr.ima.ihm.service.AdherentService;
import fr.ima.ihm.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SommePartsController {
    @Autowired
    private PartsService dao;

    @GetMapping("/sum-nombre")
    public String afficherSommeInvestis(Model model) {
        model.addAttribute("somme", dao.sum_nombre());
        return "sommeInvectis";
    }



}
