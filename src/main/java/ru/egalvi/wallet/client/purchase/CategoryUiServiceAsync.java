package ru.egalvi.wallet.client.purchase;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.egalvi.wallet.shared.domain.Category;

import java.util.ArrayList;

public interface CategoryUiServiceAsync {
    void createCategory(Category category, AsyncCallback<Void> callback);

    void getAllCategories(AsyncCallback<ArrayList<Category>> callback);
}
