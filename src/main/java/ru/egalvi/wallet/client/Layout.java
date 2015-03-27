package ru.egalvi.wallet.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Layout extends Composite {
    interface MyUiBinder extends UiBinder<Widget, Layout> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public Layout() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
