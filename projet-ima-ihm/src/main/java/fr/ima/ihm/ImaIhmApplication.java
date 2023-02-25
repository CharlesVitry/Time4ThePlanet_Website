package fr.ima.ihm;

import fr.ima.ihm.beans.Adherents;
import fr.ima.ihm.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ImaIhmApplication {

	@Autowired
	private static AdherentService adherentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImaIhmApplication.class, args);

	}

}
