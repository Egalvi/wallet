package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
    TextBox name;

    private Category category;

    private boolean editable;

    public CategoryAddWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Category getCategory() {
        Category category = new Category();
        category.setName(name.getValue());
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        name.setValue(category.getName());
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        name.setEnabled(editable);
        saveButton.setEnabled(editable);
    }
}
