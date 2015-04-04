package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
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

public class PurchaseAddWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, PurchaseAddWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private Category category;

    @UiField
    TextBox name;
    @UiField
    TextBox date;
    @UiField
    TextBox price;
    @UiField
    TextBox comment;
    @UiField
    TextBox quantity;
    @UiField
    TextBox unit;
    @UiField
    Button addButton;

    public PurchaseAddWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @UiHandler("addButton")
    public void addButtonHandler(ClickEvent clickEvent) {
        Purchase purchase = new Purchase();
        purchase.setName(name.getText());
        purchase.setDate(new Date());
        purchase.setPrice(Long.parseLong(price.getText()));
        purchase.setComment(comment.getText());
        purchase.setQuantity(Double.parseDouble(quantity.getText()));
        purchase.setUnit(unit.getText());
        if (category.getPurchases() == null) {
            category.setPurchases(new ArrayList<Purchase>());
        }
        category.getPurchases().add(purchase);

        Resource resource = new Resource(GWT.getModuleBaseURL());
        CategoryRestService categoryRestService = GWT.create(CategoryRestService.class);
        ((RestServiceProxy) categoryRestService).setResource(resource);
        categoryRestService.update(category.getId(), category, new MethodCallback<Category>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("D'oh! " + throwable);
            }

            @Override
            public void onSuccess(Method method, Category category) {
                Window.alert("Yeah!");
            }
        });
    }
}
