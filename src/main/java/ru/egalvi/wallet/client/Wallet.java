package ru.egalvi.wallet.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Wallet implements EntryPoint {

    public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

    public void onModuleLoad() {
        RootPanel.get().add(GWT.<Widget>create(Layout.class));
    }

}