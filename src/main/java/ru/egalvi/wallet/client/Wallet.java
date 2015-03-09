package ru.egalvi.wallet.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import ru.egalvi.wallet.client.crud.CrudWidget;
import ru.egalvi.wallet.client.purchase.CategoryUiService;
import ru.egalvi.wallet.client.purchase.CategoryUiServiceAsync;
import ru.egalvi.wallet.shared.domain.Category;

import java.util.ArrayList;

public class Wallet implements EntryPoint {

    private CategoryUiServiceAsync categoryUiService = GWT.create(CategoryUiService.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        CrudWidget crudWidget = GWT.create(CrudWidget.class);
        RootPanel.get().add(crudWidget);

        AsyncCallback<ArrayList<Category>> callback = new AsyncCallback<ArrayList<Category>>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(ArrayList<Category> result) {
                Window.alert(result.toString());
            }
        };

        // Make the call to the stock price service.
        categoryUiService.getAllCategories(callback);
    }
}