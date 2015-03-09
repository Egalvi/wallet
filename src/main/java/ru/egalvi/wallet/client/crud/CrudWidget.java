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


public class CrudWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, CrudWidget> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Button addButton;
    @UiField
    Button updateButton;
    @UiField
    Button deleteButton;


    public CrudWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("addButton")
    public void addButtonHandler(ClickEvent clickEvent){
        Window.alert("Add btn clicked");
    }

    @UiHandler("updateButton")
    public void updateButtonHandler(ClickEvent clickEvent){
        Window.alert("Update btn clicked");
    }

    @UiHandler("deleteButton")
    public void deleteButtonHandler(ClickEvent clickEvent){
        Window.alert("Delete btn clicked");
    }
}
