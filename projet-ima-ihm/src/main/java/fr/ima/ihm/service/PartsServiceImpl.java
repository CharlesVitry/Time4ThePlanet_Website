package fr.ima.ihm.service;

import fr.ima.ihm.beans.Shares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Date;

@Service
public class PartsServiceImpl implements PartsService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AdherentService adherentsService;

    @Override
    public Object sum_nombre() {
        String url = "http://localhost:4500/parts/sum-nombre";
        Object response = restTemplate.getForObject(url, Object.class, int.class);
        return response;
    }

    @Override
    public Shares create_paiement(String memberId, int amount) {

        String url = "http://localhost:4500/parts/create";

        fr.ima.ihm.beans.Shares shares = new fr.ima.ihm.beans.Shares();
        shares.setPaiement_situation("payé");
        shares.setPaiement_method("VISA");
        shares.setPaiement_date(new Date());
        shares.setNumber(amount);
        shares.setAdherents(adherentsService.findByEmail(memberId));
        shares.setDesc("Achat de parts");

        fr.ima.ihm.beans.Shares ret = null;
        try {
            fr.ima.ihm.service.dto.Shares sharesDTO = convertSharesBeanToDTO(shares);

            // Appel du WS Rest
            ResponseEntity<fr.ima.ihm.beans.Shares> response = restTemplate.postForEntity(url, sharesDTO, fr.ima.ihm.beans.Shares.class);
            ret = response.getBody();

        } catch (ParseException e) {
            // Gérer un message d'erreur
        }

        return ret;
    }

    private fr.ima.ihm.service.dto.Shares convertSharesBeanToDTO(fr.ima.ihm.beans.Shares bean) throws ParseException {
        fr.ima.ihm.service.dto.Shares dto = new fr.ima.ihm.service.dto.Shares();

        dto.setDesc(bean.getDesc());
        dto.setAdditional_fee(bean.getAdditional_fee());
        dto.setNumber(bean.getNumber());
        dto.setPaiement_date(bean.getPaiement_date());
        dto.setPaiement_method(bean.getPaiement_method());
        dto.setPaiement_situation(bean.getPaiement_situation());

        fr.ima.ihm.service.dto.Adherents adherentsDTO = new fr.ima.ihm.service.dto.Adherents();
        adherentsDTO.setBirthDate(bean.getAdherents().getBirthDate());
        adherentsDTO.setGender(bean.getAdherents().getGender());
        adherentsDTO.setE_mail(bean.getAdherents().getE_mail());
        adherentsDTO.setLastName(bean.getAdherents().getLastName());
        adherentsDTO.setFirstName(bean.getAdherents().getFirstName());
        adherentsDTO.setPrintListing(bean.getAdherents().isPrintListing());
        adherentsDTO.setResidentFrench(bean.getAdherents().isResidentFrench());

        dto.setAdherents(adherentsDTO);

        return dto;
    }

}
