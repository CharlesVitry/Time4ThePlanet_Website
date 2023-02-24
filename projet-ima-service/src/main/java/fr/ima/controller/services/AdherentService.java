package fr.ima.controller.services;

import fr.ima.controller.entities.dao.Adherent;
import fr.ima.controller.entities.dao.services.AdherentRepositoryInterface;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdherentService {

    private final AdherentRepositoryInterface adherentRepository;

    public AdherentService(AdherentRepositoryInterface adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    public Adherent getAdherentByEmail(String email) throws ChangeSetPersister.NotFoundException {
        Optional<Adherent> adherentOptional = adherentRepository.findById(email);
        if (adherentOptional.isPresent()) {
            return adherentOptional.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public Adherent createOrUpdateAdherent(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void deleteAdherentByEmail(String email) throws ChangeSetPersister.NotFoundException {
        Optional<Adherent> adherentOptional = adherentRepository.findById(email);
        if (adherentOptional.isPresent()) {
            adherentRepository.deleteById(email);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}