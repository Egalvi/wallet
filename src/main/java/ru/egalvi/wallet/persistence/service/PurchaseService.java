package ru.egalvi.wallet.persistence.service;

import ru.egalvi.wallet.persistence.domain.Category;
import ru.egalvi.wallet.persistence.domain.Purchase;

public interface PurchaseService {
    Purchase create(Purchase created);

    Purchase delete(Long id) throws EntityNotFoundException;

    Iterable<Purchase> findAll();

    Purchase findById(Long id);

    Purchase update(Purchase updated) throws EntityNotFoundException;
}
