package fr.ima.ihm.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import fr.ima.ihm.beans.Adherents;
import fr.ima.ihm.service.dto.Address;



@Service // Déclare la classe comme une classe de service 
public class AdherentServiceImpl implements AdherentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Adherents> lister() {

		// Construction de l'URL pour l'appel su WS
		String url = "http://localhost:4500/adherent/list";

		// Appel du WS Rest
		ObjectMapper mapper = new ObjectMapper();
		
		// Conversion des objets DTO issu du service projet-ima-service en objet Bean dans l'IHM
		// Cette conversion est causée par la librairie Jackson qui n'a pas suffisamment d'information
		// pour transformer le JSON en java : donc par défaut il utilise un LinkedHashMap
		// Erreur en test : java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to
		// https://stackoverflow.com/questions/28821715/java-lang-classcastexception-java-util-linkedhashmap-cannot-be-cast-to-com-test
		List<fr.ima.ihm.service.dto.Adherents> adherents = mapper.convertValue(restTemplate.getForObject(url, 
				List.class, 
				url), new TypeReference<List<fr.ima.ihm.service.dto.Adherents>>(){});
		
		List<Adherents> ret = new ArrayList<Adherents>();
		for (Iterator<fr.ima.ihm.service.dto.Adherents> iterator = adherents.iterator(); iterator.hasNext();) {
			fr.ima.ihm.service.dto.Adherents adherent = (fr.ima.ihm.service.dto.Adherents) iterator.next();

			String url_Adherent = "http://localhost:4500/parts/adherent/" + adherent.getE_mail();

			List<fr.ima.ihm.service.dto.Shares> parts = mapper.convertValue(restTemplate.getForObject(url_Adherent,
					List.class,
					url_Adherent), new TypeReference<List<fr.ima.ihm.service.dto.Shares>>(){});

			// On vérifie que l'adhérents veut bien être afficher et que sa liste de parts n'est pas vide
			if(adherent.isPrintListing() && parts.size() > 0) {
				ret.add(convertAdherentsDTOtoBean(adherent));
			}
		}
		return ret;
	}

	@Override
	public Adherents creer(Adherents adherents) {
		// Construction de l'URL pour l'appel su WS
		String url = "http://localhost:4500/adherent/create";

		Adherents ret = null;
		try {
			fr.ima.ihm.service.dto.Adherents adherentsDTO = convertAdherentsBeanToDTO(adherents);

			// Appel du WS Rest
			ResponseEntity<Adherents> response = restTemplate.postForEntity(url, adherentsDTO, Adherents.class);
			ret = response.getBody();
			
		} catch (ParseException e) {
			// Gérer un message d'erreur
		}
		
		return ret;
	}

	@Override
	public Adherents findByEmail(String email) {
		String url = "http://localhost:4500/adherent/" + email;

		// Appel du WS Rest
		ResponseEntity <Adherents> response = restTemplate.getForEntity(url, Adherents.class);
		// http://localhost:4550/verify-email?email=charles.vitry77@gmail.com
		return response.getBody();
	}

	/**
	 * Convertit un objet Model IHM en objet DTO pour appeler le service ima-service
	 * @param bean
	 * @return
	 * @throws ParseException
	 */
	private fr.ima.ihm.service.dto.Adherents convertAdherentsBeanToDTO(Adherents bean) throws ParseException {
		fr.ima.ihm.service.dto.Adherents dto = new fr.ima.ihm.service.dto.Adherents();
		
		dto.setFirstName(bean.getFirstName());
		dto.setLastName(bean.getLastName());
		dto.setBirthDate(bean.getBirthDate());
		dto.setResidentFrench(bean.isResidentFrench());
		dto.setPrintListing(bean.isPrintListing());
		dto.setE_mail(bean.getE_mail());
		dto.setGender(bean.getGender());

		// Créé une nouvelle instance d'adresse sans se soucier
		// de savoir si une même adresse existe déjà
		if (dto.getAdress() != null) {
			Address adresseDAO = new Address();
			adresseDAO.setStreet(dto.getAdress().getStreet());
			adresseDAO.setPostCode(dto.getAdress().getPostCode());
			adresseDAO.setCity(dto.getAdress().getCity());
			dto.setAdress(adresseDAO);
		}
		
		return dto;
	}
	
	/**
	 * Convertit un objet DTO issu du service ima-service en objet Model IHM 
	 * @param dto
	 * @return
	 */
	private Adherents convertAdherentsDTOtoBean(fr.ima.ihm.service.dto.Adherents dto) {
		Adherents ret = new Adherents();
		
		ret.setFirstName(dto.getFirstName());
		ret.setLastName(dto.getLastName());

		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		ret.setBirthDate(dto.getBirthDate());
		ret.setResidentFrench(dto.isResidentFrench());
		ret.setPrintListing(dto.isPrintListing());
		ret.setE_mail(dto.getE_mail());
		ret.setGender(dto.getGender());
		
		return ret;
	}
}
