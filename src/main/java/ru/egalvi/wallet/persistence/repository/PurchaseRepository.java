package ru.egalvi.wallet.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ru.egalvi.wallet.persistence.domain.Category;
import ru.egalvi.wallet.persistence.domain.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Purchase findById(Long id);
}
