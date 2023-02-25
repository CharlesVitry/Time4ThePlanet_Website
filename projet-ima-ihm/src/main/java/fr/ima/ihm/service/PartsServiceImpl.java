package fr.ima.ihm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ima.ihm.service.dto.Adherents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class PartsServiceImpl implements PartsService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object sum_nombre() {
        String url = "http://localhost:4500/parts/sum-nombre";
        Object response = restTemplate.getForObject(url, Object.class, int.class);
        return response;
    }

}
