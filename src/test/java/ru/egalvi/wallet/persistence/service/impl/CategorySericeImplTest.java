package ru.egalvi.wallet.persistence.service.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import junit.framework.TestCase;
import org.aspectj.lang.annotation.AfterReturning;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.egalvi.wallet.Application;
import ru.egalvi.wallet.persistence.domain.Category;
import ru.egalvi.wallet.persistence.domain.Purchase;
import ru.egalvi.wallet.persistence.repository.CategoryRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CategorySericeImplTest extends TestCase {

    @Resource
    private CategorySericeImpl categorySerice;
    @Resource
    private CategoryRepository categoryRepository;

    @Test
    public void testCreate() throws Exception {
        Category category = new Category();
        final Category created = categorySerice.create(category);
        Iterable<Category> categories = categorySerice.findAll();
        Iterable<Category> filtered = Iterables.filter(categories, new Predicate<Category>() {
            @Override
            public boolean apply(Category category) {
                return category.getId().equals(created.getId());
            }
        });
        assertTrue(filtered.iterator().hasNext());
    }

    @Test
    public void testCreateWithPurchases() throws Exception {
        Category category = new Category();
        List<Purchase> purchases = new ArrayList<Purchase>();
        purchases.add(new Purchase());
        category.setPurchases(purchases);

        final Category created = categorySerice.create(category);
        Iterable<Category> categories = categorySerice.findAll();
        Iterable<Category> filtered = Iterables.filter(categories, new Predicate<Category>() {
            @Override
            public boolean apply(Category category) {
                return category.getId().equals(created.getId());
            }
        });
        assertTrue(filtered.iterator().hasNext());
        Category read = filtered.iterator().next();
        Collection<Purchase> purchases1 = read.getPurchases();
        assertFalse(purchases1.isEmpty());
    }

    @Test
    public void testDelete() throws Exception {
        Category category = new Category();
        final Category created = categorySerice.create(category);
        assertNotNull(created);
        categorySerice.delete(created.getId());
        Iterable<Category> categories = categorySerice.findAll();
        Iterable<Category> filtered = Iterables.filter(categories, new Predicate<Category>() {
            @Override
            public boolean apply(Category category) {
                return category.getId().equals(created.getId());
            }
        });
        assertFalse(filtered.iterator().hasNext());
    }

    @After
    public void tearDown() {
        categoryRepository.deleteAll();
    }
}