package ru.egalvi.wallet.server.rest;

import org.springframework.web.bind.annotation.*;
import ru.egalvi.wallet.server.persistence.service.CategoryService;
import ru.egalvi.wallet.server.persistence.repository.domain.Category;
import ru.egalvi.wallet.server.persistence.repository.domain.Purchase;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("wallet/category")
public class CategoryResource {
    @Resource
    private CategoryService categoryService;

//    @RequestMapping(method = RequestMethod.GET)
//    public Iterable<Category> getAllRoot(){
//        return null;
//    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getById(@PathVariable(value = "id") Long id) {
        return categoryService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Category update(@PathVariable(value = "id") Long id, @RequestBody Category category) {
        return categoryService.update(category);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Category category) {
        categoryService.create(category);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }
}
