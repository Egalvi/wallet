package ru.egalvi.wallet.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Wallet implements EntryPoint {

    public void onModuleLoad() {
        RootPanel.get().add(GWT.<Widget>create(Layout.class));
    }

}