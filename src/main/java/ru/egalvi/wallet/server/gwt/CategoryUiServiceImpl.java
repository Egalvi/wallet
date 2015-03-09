package ru.egalvi.wallet.server.gwt;

import com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.egalvi.wallet.client.purchase.CategoryUiService;
import ru.egalvi.wallet.shared.domain.Category;

import java.util.ArrayList;

public class CategoryUiServiceImpl extends RemoteServiceServlet implements CategoryUiService {
    @Override
    public void createCategory(Category category) {

    }

    @Override
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>(5);
        Category category = new Category();
        category.setName("adfasdf");
        categories.add(category);
        category = new Category();
        category.setName("adfasdf");
        categories.add(category);
        category = new Category();
        category.setName("adfasdf");
        categories.add(category);
        category = new Category();
        category.setName("adfasdf");
        categories.add(category);
        category = new Category();
        category.setName("adfasdf");
        categories.add(category);

//        ArrayList<Category> categories = Lists.newArrayList("one", "two", "three");
        return categories;
    }
}
