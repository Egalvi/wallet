package ru.egalvi.wallet.client.crud;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchasesViewModel implements TreeViewModel {

    /**
     * Get the {@link NodeInfo} that provides the children of the specified
     * value.
     */
    public <T> NodeInfo<?> getNodeInfo(T value) {
      /*
       * Create some data in a data provider. Use the parent value as a prefix
       * for the next level.
       */
        ListDataProvider<Category> dataProvider = new ListDataProvider<>();
        for (int i = 0; i < 2; i++) {
            Category category = new Category();
            category.setName("cat" + i);
            category.setPurchases(new ArrayList<Purchase>());
            category.setSubCategories(new ArrayList<Category>());
            for (int j = 0; j < 2; j++) {
                Category subCategory = new Category();
                subCategory.setName("subCat " + i + j);
                subCategory.setSubCategories(new ArrayList<Category>());
                category.getSubCategories().add(subCategory);
                for (int k = 0; k < 2; k++) {
                    Category subSubCategory = new Category();
                    subSubCategory.setName("subSubCat " + i + j + k);
                    subCategory.getSubCategories().add(subSubCategory);
                }
            }
            for (int j = 0; j < 2; j++) {
                Purchase purchase = new Purchase();
                purchase.setName("purchase " + i + j);
                category.getPurchases().add(purchase);
            }
            dataProvider.getList().add(category);
        }

        if (value == null) {
            // Return a node info that pairs the data with a cell.
            return new DefaultNodeInfo<Category>(dataProvider, new CategoryCell());
        } else /*if (value instanceof Category)*/{
            return new DefaultNodeInfo<Category>(new ListDataProvider<Category>((List<Category>)((Category)value).getSubCategories()), new CategoryCell());
        }
    }

    /**
     * Check if the specified value represents a leaf node. Leaf nodes cannot be
     * opened.
     */
    public boolean isLeaf(Object value) {
        // The maximum length of a value is ten characters.
        return value instanceof Purchase;
    }

    private static class CategoryCell extends AbstractCell<Category> {

        @Override
        public void render(Context context, Category value, SafeHtmlBuilder sb) {
            if (value != null) {
                sb.appendEscaped(value.getName());
            }
        }
    }
}
