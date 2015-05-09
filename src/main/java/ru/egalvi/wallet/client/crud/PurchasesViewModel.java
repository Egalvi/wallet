package ru.egalvi.wallet.client.crud;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import ru.egalvi.wallet.client.CategoryRestService;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    //TODO here I'm reading all categories every time, not good
    public <T> NodeInfo<?> getNodeInfo(T value) {
        final Category rootCategory = new Category();
        rootCategory.setName("/");

        Purchase purchase = new Purchase();
        purchase.setName("One");
        purchase.setDate(new Date());
        purchase.setComment("sdfasdf");
        purchase.setPrice(43543l);
        purchase.setUnit("df");
        purchase.setQuantity(3.0);
        rootCategory.setPurchases(new ArrayList<Purchase>());
        rootCategory.getPurchases().add(purchase);


        Resource resource = new Resource(GWT.getModuleBaseURL());
        CategoryRestService categoryRestService = GWT.create(CategoryRestService.class);
        ((RestServiceProxy) categoryRestService).setResource(resource);

        categoryRestService.getAllCategories(new MethodCallback<Collection<Category>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("dough " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, Collection<Category> categories) {
                rootCategory.setSubCategories(new ArrayList<Category>(categories));
            }
        });

        if (value == null) {
            // Return a node info that pairs the data with a cell.
//            rootCategory.setSubCategories(dataProvider.getList());
            ListDataProvider<Category> rootDataProvider = new ListDataProvider<>(/*Lists.newArrayList(rootCategory)*/);
            rootDataProvider.getList().add(rootCategory);
            return new DefaultNodeInfo<Category>(rootDataProvider, new CategoryCell(), selectionModel,
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
