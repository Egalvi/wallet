package ru.egalvi.wallet.server.persistence.service.impl;

import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
public class CategorySericeImplTest {

    @Test
    public void Foo(){}

//    @Resource
//    private CategorySericeImpl categorySerice;
//    @Resource
//    private CategoryRepository categoryRepository;
//
//    @Test
//    public void testCreate() throws Exception {
//        Category category = new Category();
//        final Category created = categorySerice.create(category);
//        Iterable<Category> categories = categorySerice.findAll();
//        Iterable<Category> filtered = Iterables.filter(categories, new Predicate<Category>() {
//            @Override
//            public boolean apply(Category category) {
//                return category.getId().equals(created.getId());
//            }
//        });
//        assertTrue(filtered.iterator().hasNext());
//    }
//
//    @Test
//    public void testCreateWithPurchases() throws Exception {
//        Category category = new Category();
//        List<Purchase> purchases = new ArrayList<Purchase>();
//        purchases.add(new Purchase());
//        category.setPurchases(purchases);
//
//        final Category created = categorySerice.create(category);
//        Iterable<Category> categories = categorySerice.findAll();
//        Iterable<Category> filtered = Iterables.filter(categories, new Predicate<Category>() {
//            @Override
//            public boolean apply(Category category) {
//                return category.getId().equals(created.getId());
//            }
//        });
//        assertTrue(filtered.iterator().hasNext());
//        Category read = filtered.iterator().next();
//        Collection<Purchase> purchases1 = read.getPurchases();
//        assertFalse(purchases1.isEmpty());
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        Category category = new Category();
//        final Category created = categorySerice.create(category);
//        assertNotNull(created);
//        categorySerice.delete(created.getId());
//        Iterable<Category> categories = categorySerice.findAll();
//        Iterable<Category> filtered = Iterables.filter(categories, new Predicate<Category>() {
//            @Override
//            public boolean apply(Category category) {
//                return category.getId().equals(created.getId());
//            }
//        });
//        assertFalse(filtered.iterator().hasNext());
//    }
//
//    @After
//    public void tearDown() {
//        categoryRepository.deleteAll();
//    }
}