package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
import ru.egalvi.wallet.client.Wallet;
import ru.egalvi.wallet.shared.domain.Category;

public class CrudWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, CrudWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private static MethodCallback<Void> callback = new MethodCallback<Void>() {
        @Override
        public void onFailure(Method method, Throwable throwable) {
            Window.alert("D'oh! " + throwable.getMessage());
        }

        @Override
        public void onSuccess(Method method, Void aVoid) {
//                    Window.alert("yahoo");
        }
    };

    @UiField(provided = true)
    CellTree cellBrowser;
    @UiField
    CategoryAddWidget createWidget;
    @UiField
    CategoryAddWidget viewWidget;

    private final PurchasesViewModel viewModel;

    private Category selectedCategory = null;

    public CrudWidget() {
        Resource resource = new Resource(GWT.getModuleBaseURL());
        final CategoryRestService categoryRestService = GWT.create(CategoryRestService.class);
        ((RestServiceProxy) categoryRestService).setResource(resource);

        SingleSelectionModel<Object> selectionModel = new SingleSelectionModel<>();
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                Object source = ((SingleSelectionModel) selectionChangeEvent.getSource()).getSelectedObject();
                selectedCategory = (Category) source;
                viewWidget.setCategory(selectedCategory);
                Wallet.EVENT_BUS.fireEvent(new CategorySelectedEvent(selectedCategory));
            }
        });
        viewModel = new PurchasesViewModel(selectionModel);
        cellBrowser = new CellTree(viewModel, null);
        cellBrowser.setAnimationEnabled(true);
        initWidget(uiBinder.createAndBindUi(this));
        viewWidget.getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Category newCategory = viewWidget.getCategory();
                //TODO refresh view on every update
                if (newCategory.getId() != null) {
                    categoryRestService.update(newCategory.getId(), newCategory, new MethodCallback<Category>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("D'oh! " + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, Category response) {

                        }
                    });
                }
            }
        });
        createWidget.getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Category newCategory = createWidget.getCategory();
                //TODO refresh view on every update
                if (newCategory.getId() != null) {
                    categoryRestService.update(newCategory.getId(), newCategory, new MethodCallback<Category>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("D'oh! " + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, Category response) {

                        }
                    });
                } else {
                    if (selectedCategory.getId() == null) {
                        selectedCategory.getSubCategories().add(newCategory);
                        categoryRestService.create(newCategory, callback);
                    } else {
                        selectedCategory.getSubCategories().add(newCategory);
                        categoryRestService.create(selectedCategory, callback);
                    }
                }
            }
        });


    }
}
