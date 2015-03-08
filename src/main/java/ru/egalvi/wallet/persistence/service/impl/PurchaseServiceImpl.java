package ru.egalvi.wallet.persistence.service.impl;

import org.springframework.stereotype.Service;
import ru.egalvi.wallet.persistence.domain.Purchase;
import ru.egalvi.wallet.persistence.repository.PurchaseRepository;
import ru.egalvi.wallet.persistence.service.EntityNotFoundException;
import ru.egalvi.wallet.persistence.service.PurchaseService;

import javax.annotation.Resource;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase create(Purchase created) {
        return purchaseRepository.save(created);
    }

    @Override
    public Purchase delete(Long id) throws EntityNotFoundException {
        Purchase deleted = purchaseRepository.findById(id);
        purchaseRepository.delete(id);
        return deleted;
    }

    @Override
    public Iterable<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Purchase update(Purchase updated) throws EntityNotFoundException {
        return purchaseRepository.save(updated);
    }
}
