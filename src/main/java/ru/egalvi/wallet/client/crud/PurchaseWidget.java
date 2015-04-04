package ru.egalvi.wallet.client.crud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ru.egalvi.wallet.client.Wallet;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

import java.util.List;

public class PurchaseWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, PurchaseWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField(provided = true)
    CellTable<Purchase> cellTable;
    @UiField
    PurchaseAddWidget purchaseAdd;

    public PurchaseWidget() {
        cellTable = new CellTable<>();

        TextColumn<Purchase> nameColumn = new TextColumn<Purchase>() {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getName();
            }
        };
        cellTable.addColumn(nameColumn,"Name");

        TextColumn<Purchase> dateColumn = new TextColumn<Purchase>() {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getDate().toString();
            }
        };
        cellTable.addColumn(dateColumn,"Date");

        TextColumn<Purchase> priceColumn = new TextColumn<Purchase>() {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getPrice().toString();
            }
        };
        cellTable.addColumn(priceColumn,"Price");

        TextColumn<Purchase> commentColumn = new TextColumn<Purchase>() {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getComment();
            }
        };
        cellTable.addColumn(commentColumn,"Comment");

        TextColumn<Purchase> quantityColumn = new TextColumn<Purchase>() {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getQuantity().toString();
            }
        };
        cellTable.addColumn(quantityColumn,"Quantity");

        TextColumn<Purchase> unitColumn = new TextColumn<Purchase>() {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getUnit();
            }
        };
        cellTable.addColumn(unitColumn,"Unit");

        Wallet.EVENT_BUS.addHandler(CategorySelectedEvent.TYPE, new CategorySelectedEvent.CategorySelectedEventHandler() {
            @Override
            public void onCategorySelected(CategorySelectedEvent сategorySelectedEvent) {
                //TODO possible NPE
                Category category = сategorySelectedEvent.getCategory();
                cellTable.setRowData((List<Purchase>) category.getPurchases());
                purchaseAdd.setCategory(category);
            }
        });

        initWidget(uiBinder.createAndBindUi(this));
    }
}
