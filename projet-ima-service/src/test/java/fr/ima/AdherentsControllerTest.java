package fr.ima;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ima.controller.entities.dto.Address;
import fr.ima.controller.entities.dto.Adherents;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdherentsControllerTest {
    @LocalServerPort
	protected int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    private static String identifiant_adherent;
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
	private static Logger logger = Logger.getLogger(AdherentsControllerTest.class.getName());


    @Test
    @Order(1)
    public void testCreateAdherents() throws Exception {
        Adherents dummy = new Adherents();
        dummy.setGender("H");
        dummy.setLastName("MACRON");
        dummy.setFirstName("Emmanuel");
        dummy.setBirthDate(DateUtil.parse("1977-12-21"));
        dummy.setResidentFrench(true);
        dummy.setPrintListing(true);
        dummy.setE_mail("macron@yahoo.fr");

        Address address = new Address();
        address.setStreet("57 rue du Faubourg-Saint-Honoré");
        address.setPostCode("49000");
        address.setCity("75008");
        dummy.setAdress(address);



        logger.info("Object to insert : "+OBJECT_MAPPER.writeValueAsString(dummy));

        ResponseEntity<Adherents> response = testRestTemplate.postForEntity("http://localhost:"+port+"/adherent/create", dummy, Adherents.class);
        assertTrue(response.getBody() != null);

        identifiant_adherent = dummy.getE_mail();

        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
    }


    @Test
    @Order(2)
    public void testGetAdherents() throws Exception {
    	ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:"+port+"/adherent", List.class);
    	assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    	assertTrue(response.getBody().size() == 2);
    }

    @Test
    @Order(3)    
    public void testGetAdherentsNotExistDetail() throws Exception {

    	ResponseEntity<Adherents> response = testRestTemplate.getForEntity("http://localhost:"+port+"/adherent/11111", Adherents.class);
        logger.info("Réponse à du get à id qui n'existe pas"+OBJECT_MAPPER.writeValueAsString(response.getStatusCode()));

    	assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }
    
    @Test
    @Order(4)
    public void testGetAdherentsDetail() throws Exception {
        logger.info("email de l'adherent recherché :  "+OBJECT_MAPPER.writeValueAsString(identifiant_adherent));
    	ResponseEntity<Adherents> response = testRestTemplate.getForEntity("http://localhost:"+port+"/adherent/"+identifiant_adherent, Adherents.class);
        logger.info("Réponse à la recherche de l'adherent "+OBJECT_MAPPER.writeValueAsString(response.getStatusCode()));

    	assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }    
    



    @Test
    @Order(5)
    public void testUpdateAdherents() throws Exception {
        ResponseEntity<Adherents> responseGet = testRestTemplate.getForEntity("http://localhost:"+port+"/adherent/"+identifiant_adherent, Adherents.class);
        assertTrue(responseGet.getStatusCode().equals(HttpStatus.OK));
        Adherents adherentToUpdate = responseGet.getBody();

        adherentToUpdate.setFirstName("Manu");
        adherentToUpdate.getAdress().setPostCode("49003");

        logger.info("Object to update : "+OBJECT_MAPPER.writeValueAsString(adherentToUpdate));

        //ResponseEntity<Void> responsePut = testRestTemplate.put("http://localhost:"+port+"/adherent/"+identifiant_adherent, adherentToUpdate);
        ResponseEntity<Void> responsePut =  testRestTemplate.exchange("http://localhost:"+port+"/adherent/"+identifiant_adherent, HttpMethod.PUT, new HttpEntity<>(adherentToUpdate), Void.class);
        assertTrue(responsePut.getStatusCode().equals(HttpStatus.OK));

        ResponseEntity<Adherents> responseGetUpdated = testRestTemplate.getForEntity("http://localhost:"+port+"/adherent/"+identifiant_adherent, Adherents.class);
        assertTrue(responseGetUpdated.getStatusCode().equals(HttpStatus.OK));
        Adherents adherentUpdated = responseGetUpdated.getBody();

        assertTrue(adherentUpdated.getFirstName().equals("Manu"));
        assertTrue(adherentUpdated.getAdress().getPostCode().equals("49003"));
    }

    @Test
    @Order(6)
    public void testDeleteAdherents() throws Exception {
        // Suppression de l'adhérent créé dans le test précédent => normalement avec l'annotation Order ça devrait passer mtn
        ResponseEntity<Void> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/adherent/" + identifiant_adherent,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));

        // Vérification que l'adhérent a bien été supprimé
        ResponseEntity<Adherents> responseGet = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/adherent/" + identifiant_adherent,
                Adherents.class
        );

        assertTrue(responseGet.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }
}