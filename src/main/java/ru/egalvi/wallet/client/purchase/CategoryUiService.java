package ru.egalvi.wallet.client.purchase;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.egalvi.wallet.shared.domain.Category;

import java.util.ArrayList;

@RemoteServiceRelativePath("category")
public interface CategoryUiService extends RemoteService {
    void createCategory(Category category);

    ArrayList<Category> getAllCategories();
}
