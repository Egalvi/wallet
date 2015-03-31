package ru.egalvi.wallet.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.egalvi.wallet.shared.domain.Category;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface CategoryRestService extends RestService {
    @POST
    @Path("category")
    public void create(Category category, MethodCallback<Void> callback);

    @GET
    @Path("category")
    public void getAllCategories(MethodCallback<Iterable<Category>> callback);
}
