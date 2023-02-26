package fr.ima.ihm.service;

import fr.ima.ihm.beans.Shares;

public interface PartsService {
    Object sum_nombre();

    Shares create_paiement(String memberId, int amount);
}
