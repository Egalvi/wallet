package ru.egalvi.wallet.server.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ru.egalvi.wallet.server.persistence.domain.Category;

import java.util.Collection;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findById(Long id);
    Collection<Category> findByName(String name);
}
