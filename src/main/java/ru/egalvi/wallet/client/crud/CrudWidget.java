package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import ru.egalvi.wallet.client.CategoryRestService;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

public class CrudWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, CrudWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private static final String JSON_URL = GWT.getModuleBaseURL() + "category";

    @UiField
    Button addButton;
    @UiField
    TextBox name;
    @UiField(provided = true)
    CellTree cellBrowser;


    PurchaseWidget purchaseWidget;
    public void setCellTable(PurchaseWidget purchaseWidget){
        this.purchaseWidget=purchaseWidget;
    }

    private final PurchasesViewModel viewModel;

    private Category selectedCategory = null;

    public CrudWidget() {
        SingleSelectionModel<Object> selectionModel = new SingleSelectionModel<>();
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                Object source = ((SingleSelectionModel) selectionChangeEvent.getSource()).getSelectedObject();
                selectedCategory = (Category) source;
                if(purchaseWidget!=null){
                    purchaseWidget.setData(selectedCategory);
                }
            }
        });
        viewModel = new PurchasesViewModel(selectionModel);
        cellBrowser = new CellTree(viewModel, null);
        cellBrowser.setAnimationEnabled(true);
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("addButton")
    public void addButtonHandler(ClickEvent clickEvent) {
        Resource resource = new Resource(GWT.getModuleBaseURL());
        CategoryRestService categoryRestService = GWT.create(CategoryRestService.class);
        ((RestServiceProxy)categoryRestService).setResource(resource);

        Category newCategory = new Category();
        newCategory.setName(name.getValue());

        //TODO refresh view on every update
        if (selectedCategory.getId() == null) {
            selectedCategory.getSubCategories().add(newCategory);
            categoryRestService.create(newCategory, new MethodCallback<Void>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    Window.alert("dough "+ throwable.getMessage());
                }

                @Override
                public void onSuccess(Method method, Void aVoid) {
//                    Window.alert("yahoo");
                }
            });
        } else {
            selectedCategory.getSubCategories().add(newCategory);
            categoryRestService.create(selectedCategory, new MethodCallback<Void>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    Window.alert("dough "+ throwable.getMessage());
                }

                @Override
                public void onSuccess(Method method, Void aVoid) {
//                    Window.alert("yahoo");
                }
            });
        }
    }

}
