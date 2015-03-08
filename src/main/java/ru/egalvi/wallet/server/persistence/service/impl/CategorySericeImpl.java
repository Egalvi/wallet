package ru.egalvi.wallet.server.persistence.service.impl;

import org.springframework.stereotype.Service;
import ru.egalvi.wallet.server.persistence.domain.Category;
import ru.egalvi.wallet.server.persistence.repository.CategoryRepository;
import ru.egalvi.wallet.server.persistence.service.CategoryService;
import ru.egalvi.wallet.server.persistence.service.EntityNotFoundException;

import javax.annotation.Resource;

@Service
public class CategorySericeImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category created) {
        return categoryRepository.save(created);
    }

    @Override
    public Category delete(Long id) throws EntityNotFoundException {
        Category deleted = categoryRepository.findById(id);
        categoryRepository.delete(id);
        return deleted;
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Iterable<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category update(Category updated) throws EntityNotFoundException {
        return categoryRepository.save(updated);
    }
}
