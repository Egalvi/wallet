package ru.egalvi.wallet.server.persistence.service;

import ru.egalvi.wallet.server.persistence.domain.Category;

public interface CategoryService {
    Category create(Category created);

    Category delete(Long id) throws EntityNotFoundException;

    Iterable<Category> findAll();

    Category findById(Long id);

    Iterable<Category> findByName(String name);

    Category update(Category updated) throws EntityNotFoundException;
}
