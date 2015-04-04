package ru.egalvi.wallet.client.crud;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import ru.egalvi.wallet.shared.domain.Category;

public class CategorySelectedEvent extends GwtEvent<CategorySelectedEvent.CategorySelectedEventHandler> {

    public interface CategorySelectedEventHandler extends EventHandler {
        void onCategorySelected(CategorySelectedEvent сategorySelectedEvent);
    }

    public static Type<CategorySelectedEventHandler> TYPE = new Type<CategorySelectedEventHandler>();

    private final Category category;

    public CategorySelectedEvent(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public Type<CategorySelectedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CategorySelectedEventHandler handler) {
        handler.onCategorySelected(this);
    }
}
