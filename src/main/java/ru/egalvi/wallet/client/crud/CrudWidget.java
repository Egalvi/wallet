package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import ru.egalvi.wallet.shared.domain.Category;


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
    CellBrowser cellBrowser;

    public CrudWidget() {
        cellBrowser = new CellBrowser.Builder<Category>(new PurchasesViewModel(), null).build();
        cellBrowser.setAnimationEnabled(true);
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("addButton")
    public void addButtonHandler(ClickEvent clickEvent) {
//        Window.alert("Add btn clicked");
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, JSON_URL);
        builder.setHeader("Content-Type", "application/json");
        try {
            Request request = builder.sendRequest("{\"name\":\"" + name.getValue() + "\"}", new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
//                    if (200 == response.getStatusCode()) {
//                        updateTable(JsonUtils.<JsArray<StockData>>safeEval(response.getText()));
//                    } else {
//                        displayError("Couldn't retrieve JSON (" + response.getStatusText()
//                                + ")");
//                    }
                    int status = response.getStatusCode();
                }
            });
        } catch (RequestException e) {
            Window.alert("Couldn't retrieve JSON");
        }
    }

}
