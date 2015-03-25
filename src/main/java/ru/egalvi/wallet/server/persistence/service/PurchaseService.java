package ru.egalvi.wallet.server.persistence.service;

import ru.egalvi.wallet.server.persistence.repository.domain.Purchase;

public interface PurchaseService {
    Purchase create(Purchase created);

    Purchase delete(Long id) throws EntityNotFoundException;

    Iterable<Purchase> findAll();

    Purchase findById(Long id);

    Purchase update(Purchase updated) throws EntityNotFoundException;
}
