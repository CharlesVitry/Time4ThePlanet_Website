package fr.ima;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
import fr.ima.controller.entities.dto.Promotion;
import fr.ima.controller.entities.dto.Student;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerTest {
    @LocalServerPort
	protected int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    private static String idINE;
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
	private static Logger logger = Logger.getLogger(StudentControllerTest.class.getName());
    
    @Test
    public void testGetStudents() throws Exception {
//        when().get("/").then()
//                .body(is("Hello World!"));
    }

    @Test
    @Order(3)    
    public void testGetStudentNotExistDetail() throws Exception {

    	ResponseEntity<Student> response = testRestTemplate.getForEntity("http://localhost:"+port+"/student/11111", Student.class);
    	assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }
    
    @Test
    @Order(2)
    public void testGetStudentDetail() throws Exception {

    	ResponseEntity<Student> response = testRestTemplate.getForEntity("http://localhost:"+port+"/student/"+idINE, Student.class);
    	assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }    
    
    @Test
    @Order(1)
    public void testCreateStudent() throws Exception {
    	
    	
    	
    	Student dummy = new Student();
    	dummy.setLastName("FLORANCE");
    	dummy.setFirstName("Grégory");
    	dummy.setBirthDate(DateUtil.parse("1978-01-28"));
    	
    	Address address = new Address();
    	address.setStreet("44 rue Rabelais");
    	address.setPostCode("49000");
    	address.setCity("ANGERS");
    	dummy.setAddress(address);
    	
    	Promotion promo = new Promotion();
    	promo.setCode("IMA5-2022");
    	dummy.setPromo(promo);    	
    	
    	logger.info("Object to insert : "+OBJECT_MAPPER.writeValueAsString(dummy));
    	
    	ResponseEntity<Student> response = testRestTemplate.postForEntity("http://localhost:"+port+"/student/create", dummy, Student.class);
    	assertTrue(response.getBody() != null);
    	
    	idINE = response.getBody().getINE();
    	
    	assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
     }    
    
    @Test
    public void testDeleteStudent() throws Exception {
    }        
    
    @Test
    public void testUpdateStudent() throws Exception {
    }            
}