package fr.ima;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import fr.ima.controller.AdherentController;
import fr.ima.controller.entities.dao.Parts;
import fr.ima.controller.entities.dto.Adherents;
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
import fr.ima.controller.entities.dto.Shares;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PartsControllerTest {
    @LocalServerPort
    protected int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static Long id_part;


    private static String identifiant_adherent;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static Logger logger = Logger.getLogger(AdherentsControllerTest.class.getName());
    private AdherentController adherentController;


    @Test
    @Order(1)
    public void testCreateShares() throws Exception {
        //création d'un adhérent
        Adherents dummy = new Adherents();
        dummy.setGender("H");
        dummy.setLastName("MACRON");
        dummy.setFirstName("Emmanuel");
        dummy.setBirthDate(DateUtil.parse("1977-12-21"));
        dummy.setResidentFrench(true);
        dummy.setPrintListing(true);
        dummy.setE_mail("macron2@yahoo.fr");

        Address address = new Address();
        address.setStreet("57 rue du Faubourg-Saint-Honoré");
        address.setPostCode("49000");
        address.setCity("75008");
        dummy.setAdress(address);

        ResponseEntity<Adherents> response = testRestTemplate.postForEntity("http://localhost:"+port+"/adherent/create", dummy, Adherents.class);
        assertTrue(response.getBody() != null);
        identifiant_adherent = dummy.getE_mail();
        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));

        //création d'une part

        Shares part_dummy = new Shares();
        part_dummy.setPaiement_method("Visa");
        part_dummy.setDesc("j'aime le chocolat, le réchauffement climatique menace mes plaisirs");
        part_dummy.setNumber(150);
        part_dummy.setPaiement_date(DateUtil.parse("2023-02-23"));
        part_dummy.setPaiement_situation("En cours");
        part_dummy.setAdditional_fee("NONE");

        part_dummy.setAdherents(dummy);


        logger.info("Object to insert : "+OBJECT_MAPPER.writeValueAsString(part_dummy));

        ResponseEntity<Shares> response_parts = testRestTemplate.postForEntity("http://localhost:"+port+"/parts/create", part_dummy, Shares.class);

        assertTrue(response_parts.getBody() != null);

        id_part = (long) response_parts.getBody().getId();

        assertTrue(response_parts.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    @Order(2)
    public void testGetSharesDetail() throws Exception {

        ResponseEntity<Shares> response = testRestTemplate.getForEntity("http://localhost:"+port+"/parts/"+id_part, Shares.class);
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    @Order(3)
    public void testGetSharesNotExistDetail() throws Exception {

        ResponseEntity<Shares> response = testRestTemplate.getForEntity("http://localhost:"+port+"/parts/11111", Shares.class);
        assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }


    @Test
    @Order(4)
    public void testGetparts() throws Exception {
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:"+port+"/parts", List.class);
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody().size() == 1);
    }

//    @Test
//    @Order(5)
//    public void testUpdateShares() throws Exception {
//        ResponseEntity<Shares> responseGet = testRestTemplate.getForEntity("http://localhost:"+port+"/parts/"+id_part, Shares.class);
//        assertTrue(responseGet.getStatusCode().equals(HttpStatus.OK));
//        Shares partsToUpdate = responseGet.getBody();
//
//        partsToUpdate.setPaiement_situation("Effectue");
//
//        logger.info("Object to update : "+OBJECT_MAPPER.writeValueAsString(partsToUpdate));
//
//        ResponseEntity<Void> responsePut =  testRestTemplate.exchange("http://localhost:"+port+"/parts/"+id_part, HttpMethod.PUT, new HttpEntity<>(partsToUpdate), Void.class);
//        assertTrue(responsePut.getStatusCode().equals(HttpStatus.OK));
//
//        ResponseEntity<Shares> responseGetUpdated = testRestTemplate.getForEntity("http://localhost:"+port+"/parts/"+id_part, Shares.class);
//        assertTrue(responseGetUpdated.getStatusCode().equals(HttpStatus.OK));
//        Shares partsUpdated = responseGetUpdated.getBody();
//
//        assertTrue(partsUpdated.getPaiement_situation().equals("Effectue"));
//
//    }

    @Test
    @Order(5)
    public void testDeleteShares() throws Exception {
        ResponseEntity<Void> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/parts/" + id_part,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));

        ResponseEntity<Shares> responseGet = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/parts/" + id_part,
                Shares.class
        );

        assertTrue(responseGet.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }



}