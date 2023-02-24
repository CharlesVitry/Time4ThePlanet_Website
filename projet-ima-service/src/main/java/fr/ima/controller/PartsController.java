package fr.ima.controller;

import fr.ima.controller.entities.dao.Adherent;
import fr.ima.controller.entities.dao.services.AdherentRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import fr.ima.controller.entities.dao.Parts;
import fr.ima.controller.entities.dao.services.PartsRepositoryInterface;
import fr.ima.controller.entities.dto.Adherents;
import fr.ima.controller.entities.dto.Shares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parts")
public class PartsController {

    @Autowired
    private PartsRepositoryInterface partsRepository;

    @Autowired
    private AdherentRepositoryInterface adherentRepository;

    @Autowired
    private AdherentController adherentController;

    // toutes les parts, utile pour le compteur final
    @GetMapping("/{id}")
    public ResponseEntity<Shares> getPartsById(@PathVariable int id) {
        Optional<Parts> partsOptional = partsRepository.findById((long) id);
        if (partsOptional.isPresent()) {
            Shares partsDTO = convertToDTO(partsOptional.get());
            return new ResponseEntity<>(partsDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/adherent/{id}")
    public ResponseEntity<List<Shares>> getPartsByAdherentId(@PathVariable int id) {
        Optional<Adherent> adherentOptional = adherentRepository.findById(String.valueOf((long) id));
        if (adherentOptional.isPresent()) {
            List<Parts> partsList = partsRepository.findByAdherent(adherentOptional.get());
            List<Shares> partsDTOList = new ArrayList<>();
            for (Parts parts : partsList) {
                partsDTOList.add(convertToDTO(parts));
            }
            return new ResponseEntity<>(partsDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Shares> createParts(@RequestBody Shares partsDTO) {
        Parts parts = convertToEntity(partsDTO);
        Parts savedParts = partsRepository.save(parts);
        Shares savedPartsDTO = convertToDTO(savedParts);
        return new ResponseEntity<>(savedPartsDTO, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Shares> updateParts(@PathVariable int id, @RequestBody Shares partsDTO) {
        Optional<Parts> partsOptional = partsRepository.findById((long) id);
        if (partsOptional.isPresent()) {
            Parts parts = convertToEntity(partsDTO);
            parts.setId((long) id);
            Parts updatedParts = partsRepository.save(parts);
            Shares updatedPartsDTO = convertToDTO(updatedParts);
            return new ResponseEntity<>(updatedPartsDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParts(@PathVariable int id) {
        Optional<Parts> partsOptional = partsRepository.findById((long) id);
        if (partsOptional.isPresent()) {
            partsRepository.delete(partsOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Shares convertToDTO(Parts parts) {
        Shares shares = new Shares();
        shares.setId(Math.toIntExact(parts.getId()));
        shares.setDesc(parts.getDescription());
        shares.setAdditional_fee(parts.getCouts_supp());
        shares.setNumber(parts.getNombre());
        shares.setPaiement_situation(parts.getStatus_paiement());
        shares.setPaiement_date(parts.getDate_paiement());
        shares.setPaiement_method(parts.getMethode_paiement());
        shares.setAdherents(new Adherents());

        return shares;
    }

    private Parts convertToEntity(Shares share) {
        Parts part = new Parts();
        part.setCouts_supp(share.getAdditional_fee());
        part.setDescription(share.getDesc());
        part.setNombre(share.getNumber());
        part.setDate_paiement(share.getPaiement_date());
        part.setMethode_paiement(share.getPaiement_method());
        part.setStatus_paiement(share.getPaiement_situation());
        part.setAdherent(adherentController.createDAOFromAdherentsDTO(share.getAdherents()) );
        return part;
    }



}
