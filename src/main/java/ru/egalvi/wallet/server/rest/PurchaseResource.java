package ru.egalvi.wallet.server.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.egalvi.wallet.server.persistence.repository.domain.Purchase;
import ru.egalvi.wallet.server.persistence.service.CategoryService;
import ru.egalvi.wallet.server.persistence.service.PurchaseService;
import ru.egalvi.wallet.server.persistence.service.impl.CategorySericeImpl;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;


//TODO do I really need this class?
@RestController
@RequestMapping("category/{id}/purchase")
public class PurchaseResource {
    @Resource
    private CategoryService categoryService;
    @Resource
    private PurchaseService purchaseService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Purchase> getAllForCategory(@PathParam("id") Long id) {
        return categoryService.findById(id).getPurchases();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@PathParam("id") Long id,@RequestBody Purchase purchase){

    }


}
