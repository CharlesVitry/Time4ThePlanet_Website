package fr.ima.ihm.controller;

import fr.ima.ihm.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.ima.ihm.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import fr.ima.ihm.beans.Adherents;
import fr.ima.ihm.service.AdherentService;

@Controller
public class PaiementController {
    @Autowired
    private AdherentService dao_adherent; // Appelle à l'interface du service "Etudiant" : lister, créer, MAJ, etc....

    @Autowired
    private PartsService dao_parts;

    @RequestMapping(value ="/verify-email", method = RequestMethod.GET)
    public String verifyEmail(@RequestParam("email") String email, Model model) {
        Adherents member = dao_adherent.findByEmail(email);

        if(member == null){
            String errorMessage = "Invalid email address. : "+email+" Please try again."
                    + "<a href='/register' style='text-decoration: none;'>Not registered yet? Register now!</a>";
            model.addAttribute("errorMessage", errorMessage);
            return "verifyEmail";
        }

        if (!member.isResidentFrench()){
            String errorMessage = "Sorry, you are not FRENCH resident.";
            model.addAttribute("errorMessage", errorMessage);
            return "verifyEmail";
        }

        model.addAttribute("memberId", member.getE_mail());
        return "redirect:/payment?memberId=" + member.getE_mail();

    }

    @RequestMapping(value = "/payment")
    public String showPaymentPage(@RequestParam("memberId") String memberId, Model model) {

        model.addAttribute("memberId", memberId);
        return "payment";
    }

    @PostMapping("/submit-payment")
    public String submitPayment(@RequestParam("memberId") String memberId, @RequestParam("amount") int amount, Model model) {

        dao_parts.create_paiement(memberId, amount);

        model.addAttribute("successMessage", "Payment successful. Thank you!");
        return "payment";
    }
}