package ru.egalvi.wallet.server.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ru.egalvi.wallet.server.persistence.domain.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Purchase findById(Long id);
}
