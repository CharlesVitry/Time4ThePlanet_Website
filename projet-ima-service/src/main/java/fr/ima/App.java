package fr.ima;

import fr.ima.controller.entities.dao.Adherent;
import fr.ima.controller.entities.dao.Adresse;
import fr.ima.controller.entities.dao.Parts;
import fr.ima.controller.entities.dao.services.AdherentRepositoryInterface;
import fr.ima.controller.entities.dao.services.PartsRepositoryInterface;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class App {

    @Autowired
    private AdherentRepositoryInterface adherentRepository;

    @Autowired
    private PartsRepositoryInterface partsRepository;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

    @PostConstruct // car sinon l'interface n'était pas encore initialisée
    public void init() {

        // create example student
        Adherent charles = new Adherent();
        charles.setNom("Vitry");
        charles.setPrenom("Charles");
        charles.setResidentFrancais(true);
        charles.setCivilite("Monsieur");
        charles.setEmail("charles.vitry77@gmail.com");
        charles.setDateNaissance(DateUtil.parse("2000-08-15"));
        charles.setAffichageLister(true);

        Adresse adresse = new Adresse();
        adresse.setRue("Rue");
        adresse.setVille("Angers");
        adresse.setCodePostal("49000");

        charles.setAdresse(adresse);

        Adherent clovis = new Adherent();
        clovis.setNom("Deletre");
        clovis.setPrenom("Clovis");
        clovis.setResidentFrancais(true);
        clovis.setCivilite("Monsieur");
        clovis.setEmail("clovis@gmail.com");
        clovis.setDateNaissance(DateUtil.parse("2002-10-14"));
        clovis.setAffichageLister(true);
        clovis.setAdresse(adresse);

        Adherent jean = new Adherent();
        jean.setNom("Dupont");
        jean.setPrenom("Jean");
        jean.setResidentFrancais(true);
        jean.setCivilite("Monsieur");
        jean.setEmail("jean@gmail.com");
        jean.setDateNaissance(DateUtil.parse("2000-10-14"));
        jean.setAffichageLister(false);
        jean.setAdresse(adresse);

        Parts parts = new Parts();
        parts.setAdherent(charles);
        parts.setNombre(1000);

        Parts parts2 = new Parts();
        parts2.setAdherent(clovis);
        parts2.setNombre(1000);

        Parts parts3 = new Parts();
        parts3.setAdherent(jean);
        parts3.setNombre(1000);
        parts3.setDescription("test");  
        // save all
        adherentRepository.save(charles);
        adherentRepository.save(clovis);
        adherentRepository.save(jean);

        partsRepository.save(parts);
        partsRepository.save(parts2);
        partsRepository.save(parts3);}
}