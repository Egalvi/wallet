package ru.egalvi.wallet.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import ru.egalvi.wallet.client.crud.CrudWidget;
import ru.egalvi.wallet.client.crud.PurchaseWidget;

public class Layout extends Composite {
    interface MyUiBinder extends UiBinder<Widget, Layout> {
    }

    @UiField
    CrudWidget crudWidget;
    @UiField
    PurchaseWidget purchaseWidget;

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public Layout() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
