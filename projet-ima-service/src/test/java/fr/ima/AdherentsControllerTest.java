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
    @Order(4)    
    public void testGetAdherents() throws Exception {
    	ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:"+port+"/adherents", List.class);
    	assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    	assertTrue(response.getBody().size() == 1);
    }

    @Test
    @Order(3)    
    public void testGetAdherentsNotExistDetail() throws Exception {

    	ResponseEntity<Adherents> response = testRestTemplate.getForEntity("http://localhost:"+port+"/adherent/11111", Adherents.class);
    	assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }
    
    @Test
    @Order(2)
    public void testGetAdherentsDetail() throws Exception {

    	ResponseEntity<Adherents> response = testRestTemplate.getForEntity("http://localhost:"+port+"/adherent/"+identifiant_adherent, Adherents.class);
    	assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }    
    
    @Test
    @Order(1)
    public void testCreateAdherents() throws Exception {
    	
    	
    	
    	Adherents dummy = new Adherents();
    	dummy.setLastName("MACRON");
    	dummy.setFirstName("Emmanuel");
    	dummy.setBirthDate(DateUtil.parse("1977-12-21"));
    	dummy.setResidentFrench(true);
    	dummy.setPrintListing(true);
    	
    	dummy.setNumberParts(51);
    	
    	Address address = new Address();
    	address.setStreet("57 rue du Faubourg-Saint-Honoré");
    	address.setPostCode("49000");
    	address.setCity("75008");
    	dummy.setAdress(address);
    	
    	    	
    	
    	logger.info("Object to insert : "+OBJECT_MAPPER.writeValueAsString(dummy));
    	
    	ResponseEntity<Adherents> response = testRestTemplate.postForEntity("http://localhost:"+port+"/adherent/create", dummy, Adherents.class);
    	assertTrue(response.getBody() != null);
    	
    	identifiant_adherent = String.valueOf(response.getBody().getIdentifiant_adherent());
    	
    	assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
     }    
    
    @Test
    public void testDeleteAdherents() throws Exception {
    }        
    
    @Test
    public void testUpdateAdherents() throws Exception {
    }            
}