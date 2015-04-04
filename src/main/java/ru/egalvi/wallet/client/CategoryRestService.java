package ru.egalvi.wallet.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.egalvi.wallet.shared.domain.Category;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collection;

public interface CategoryRestService extends RestService {
    @POST
    @Path("category")
    public void create(Category category, MethodCallback<Void> callback);

    @GET
    @Path("category")
    public void getAllCategories(MethodCallback<Collection<Category>> callback);

    @POST
    @Path("category/{id}")
    public void update(@PathParam("id") Long id, Category category, MethodCallback<Category> callback);
}
