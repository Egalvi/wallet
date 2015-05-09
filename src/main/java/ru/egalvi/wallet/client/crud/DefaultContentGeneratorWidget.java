package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import ru.egalvi.wallet.client.CategoryRestService;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultContentGeneratorWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, DefaultContentGeneratorWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Button generateBtn;

    public DefaultContentGeneratorWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("generateBtn")
    public void generateButtonHandler(ClickEvent clickEvent) {
        List<Category> categories = new ArrayList<>(5);
        List<String> categoriyNames = new ArrayList<>(5);
        categoriyNames.add("One Cat");
        categoriyNames.add("Two Cat");
        categoriyNames.add("Three Cat");
        categoriyNames.add("Four Cat");

        List<String> subcatNames = new ArrayList<>();
        subcatNames.add("SubOne");
        subcatNames.add("SubTwo");

        List<String> purchNames = new ArrayList<>();
        purchNames.add("Purch 1");
        purchNames.add("Purch 2");

        Category category;
        for (String categoryName : categoriyNames) {
            category = new Category();
            category.setName(categoryName);
            categories.add(category);

            List<Category> subcats = null;
            for (String subcatName : subcatNames) {
                subcats = new ArrayList<>();
                Category subcat = new Category();
                subcat.setName(categoryName + " " + subcatName);
                subcats.add(subcat);
                List<Purchase> purchs = null;
                for (String purchName : purchNames) {
                    purchs = new ArrayList<>();
                    Purchase purchase = new Purchase();
                    purchase.setName(categoryName + " " + subcatName + " " + purchName);
                    purchase.setDate(new Date());
                    purchase.setQuantity(5.0);
                    purchase.setComment("dfsdf");
                    purchase.setPrice(456l);
                    purchase.setUnit("kg");
                    purchs.add(purchase);
                }
                subcat.setPurchases(purchs);
            }
            category.setSubCategories(subcats);
        }

        Resource resource = new Resource(GWT.getModuleBaseURL());
        CategoryRestService categoryRestService = GWT.create(CategoryRestService.class);
        ((RestServiceProxy) categoryRestService).setResource(resource);

        for (Category cat : categories) {
            categoryRestService.create(cat, new MethodCallback<Void>() {
                @Override
                public void onFailure(Method method, Throwable exception) {
                    Window.alert("D'oh! " + exception);
                }

                @Override
                public void onSuccess(Method method, Void response) {

                }
            });
        }
    }
}
