package fr.ima.ihm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.ima.ihm.beans.Student;



@Service // Déclare la classe comme une classe de service 
public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Student> lister() {

		// Construction de l'URL pour l'appel su WS
		String url = "http://localhost:8081/students";

		// Appel du WS Rest
//		List<Student> students = (List<Student>) restTemplate.getForObject(url, 
//				List.class, 
//				url);
		
		//Bouchon
		List dummyStudents = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setFirstName("Prénom un");
		student1.setLastName("Nom un");
		student1.setBirthDate(new Date());
		dummyStudents.add(student1);
		
		return dummyStudents;
	}

	@Override
	public Student creer(Student student) {
		// Construction de l'URL pour l'appel su WS
		String url = "http://localhost:8081/student/create";

		// Appel du WS Rest
		ResponseEntity<Student> ret = restTemplate.postForEntity(url, student, Student.class);
		
		return ret.getBody();
	}

}
