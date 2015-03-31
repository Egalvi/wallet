package ru.egalvi.wallet.client.crud;

import com.google.common.collect.Lists;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.*;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchasesViewModel implements TreeViewModel {

    SingleSelectionModel<Object> selectionModel;

    public PurchasesViewModel(SingleSelectionModel<Object> selectionModel) {
        this.selectionModel = selectionModel;
    }

    /**
     * Get the {@link NodeInfo} that provides the children of the specified
     * value.
     */
    public <T> NodeInfo<?> getNodeInfo(T value) {
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
            Category rootCategory = new Category();
            rootCategory.setName("/");
            rootCategory.setSubCategories(dataProvider.getList());
            ListDataProvider<Category> rootDataProvider = new ListDataProvider<>(/*Lists.newArrayList(rootCategory)*/);
            rootDataProvider.getList().add(rootCategory);
            return new DefaultNodeInfo<Category>(rootDataProvider,new CategoryCell(), selectionModel,
                    DefaultSelectionEventManager.<Category>createDefaultManager(), null);
        } else /*if (value instanceof Category)*/ {
            return new DefaultNodeInfo<Category>(
                    new ListDataProvider<Category>((List<Category>) ((Category) value).getSubCategories()),
                    new CategoryCell(),
                    selectionModel, DefaultSelectionEventManager.<Category>createDefaultManager(), null);
        }
    }

    /**
     * Check if the specified value represents a leaf node. Leaf nodes cannot be
     * opened.
     */
    public boolean isLeaf(Object value) {
//        return value instanceof Purchase;
        return value != null && (((Category) value).getSubCategories() == null || ((Category) value).getSubCategories().isEmpty());
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
