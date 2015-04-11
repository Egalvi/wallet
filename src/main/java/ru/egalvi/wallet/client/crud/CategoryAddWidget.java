package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import ru.egalvi.wallet.shared.domain.Category;

public class CategoryAddWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, CategoryAddWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Button saveButton;
    @UiField
    Button editButton;
    @UiField
    TextBox name;

    private Category category;

    private boolean editable = true;

    public CategoryAddWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        setEditable(editable);
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Category getCategory() {
        Category newCategory;
        if(category != null){
            newCategory = category;
        } else {
            newCategory = new Category();
        }
        newCategory.setName(name.getValue());
        return newCategory;
    }

    public void setCategory(Category category) {
        this.category = category;
        name.setValue(category.getName());
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        name.setEnabled(editable);
        saveButton.setVisible(editable);
        editButton.setVisible(!editable);
    }

    @UiHandler("editButton")
    void editButtonClick(ClickEvent event){
        setEditable(true);
    }
}
