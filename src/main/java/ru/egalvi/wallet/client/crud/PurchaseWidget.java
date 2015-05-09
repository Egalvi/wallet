package ru.egalvi.wallet.client.crud;

import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import ru.egalvi.wallet.client.CategoryRestService;
import ru.egalvi.wallet.client.Wallet;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PurchaseWidget extends Composite {

    public static final MethodCallback<Category> VOID_CALLBACK = new MethodCallback<Category>() {
        @Override
        public void onFailure(Method method, Throwable exception) {
            Window.alert("D'oh! " + exception);
        }

        @Override
        public void onSuccess(Method method, Category response) {
            Window.alert("Ok!");
        }
    };

    public static final MethodCallback<Category> VOID_UPDATE_CALLBACK = new MethodCallback<Category>() {
        @Override
        public void onFailure(Method method, Throwable exception) {
            Window.alert("D'oh! " + exception);
        }

        @Override
        public void onSuccess(Method method, Category response) {
            Window.alert("Ok!");
        }
    };

    interface MyUiBinder extends UiBinder<Widget, PurchaseWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private Category selectedCategory;

    @UiField(provided = true)
    CellTable<Purchase> cellTable;
    @UiField
    PurchaseAddWidget purchaseAdd;

    public PurchaseWidget() {
        Resource resource = new Resource(GWT.getModuleBaseURL());
        final CategoryRestService categoryRestService = GWT.create(CategoryRestService.class);
        ((RestServiceProxy) categoryRestService).setResource(resource);

        cellTable = new CellTable<>();

        // Purchase name.
        Column<Purchase, String> nameColumn = new Column<Purchase, String>(new EditTextCell()) {
            @Override
            public String getValue(Purchase object) {
                return object.getName();
            }
        };
        //TODO add sorting ability?
        //TODO too much code duplication
        nameColumn.setFieldUpdater(new FieldUpdater<Purchase, String>() {
            @Override
            public void update(int index, Purchase object, String value) {
                // Called when the user ends to change the value.
                selectedCategory.getPurchases().remove(object);
                object.setName(value);
                selectedCategory.getPurchases().add(object);
                categoryRestService.update(selectedCategory.getId(), selectedCategory, VOID_CALLBACK);
            }
        });
        cellTable.addColumn(nameColumn, "Name");

        Column<Purchase, Date> dateColumn = new Column<Purchase, Date>(new DatePickerCell()) {
            @Override
            public Date getValue(Purchase purchase) {
                return purchase.getDate();
            }
        };
        dateColumn.setFieldUpdater(new FieldUpdater<Purchase, Date>() {
            @Override
            public void update(int index, Purchase object, Date value) {
                selectedCategory.getPurchases().remove(object);
                object.setDate(value);
                selectedCategory.getPurchases().add(object);
                categoryRestService.update(selectedCategory.getId(), selectedCategory, VOID_CALLBACK);
            }
        });
        cellTable.addColumn(dateColumn, "Date");

        Column<Purchase, String> priceColumn = new Column<Purchase, String>(new EditTextCell()) {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getPrice().toString();
            }
        };
        priceColumn.setFieldUpdater(new FieldUpdater<Purchase, String>() {
            @Override
            public void update(int index, Purchase object, String value) {
                // Called when the user ends to change the value.
                selectedCategory.getPurchases().remove(object);
                object.setPrice(Long.parseLong(value));
                selectedCategory.getPurchases().add(object);
                categoryRestService.update(selectedCategory.getId(), selectedCategory, VOID_CALLBACK);
            }
        });
        cellTable.addColumn(priceColumn, "Price");

        Column<Purchase, String> commentColumn = new Column<Purchase, String>(new EditTextCell()) {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getComment();
            }
        };
        commentColumn.setFieldUpdater(new FieldUpdater<Purchase, String>() {
            @Override
            public void update(int index, Purchase object, String value) {
                // Called when the user ends to change the value.
                selectedCategory.getPurchases().remove(object);
                object.setComment(value);
                selectedCategory.getPurchases().add(object);
                categoryRestService.update(selectedCategory.getId(), selectedCategory, VOID_CALLBACK);
            }
        });
        cellTable.addColumn(commentColumn, "Comment");

        Column<Purchase, String> quantityColumn = new Column<Purchase, String>(new EditTextCell()) {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getQuantity().toString();
            }
        };
        quantityColumn.setFieldUpdater(new FieldUpdater<Purchase, String>() {
            @Override
            public void update(int index, Purchase object, String value) {
                // Called when the user ends to change the value.
                selectedCategory.getPurchases().remove(object);
                object.setQuantity(Double.parseDouble(value));
                selectedCategory.getPurchases().add(object);
                categoryRestService.update(selectedCategory.getId(), selectedCategory, VOID_CALLBACK);
            }
        });
        cellTable.addColumn(quantityColumn, "Quantity");

        Column<Purchase, String> unitColumn = new Column<Purchase, String>(new EditTextCell()) {
            @Override
            public String getValue(Purchase purchase) {
                return purchase.getUnit();
            }
        };
        unitColumn.setFieldUpdater(new FieldUpdater<Purchase, String>() {
            @Override
            public void update(int index, Purchase object, String value) {
                // Called when the user ends to change the value.
                selectedCategory.getPurchases().remove(object);
                object.setUnit(value);
                selectedCategory.getPurchases().add(object);
                categoryRestService.update(selectedCategory.getId(), selectedCategory, VOID_CALLBACK);
            }
        });
        cellTable.addColumn(unitColumn, "Unit");

        final List<String> categories = new ArrayList<>();
        categoryRestService.getAllCategories(new MethodCallback<Collection<Category>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, Collection<Category> response) {
                CATEGORIES = response;
                for (Category category : response) {
                    categories.add(category.getName());
                }
                CATEGORY_NAMES = categories;
                final Column<Purchase, String> categoryColumn = new Column<Purchase, String>(new SelectionCell(CATEGORY_NAMES)) {
                    @Override
                    public String getValue(Purchase object) {
                        return selectedCategory.getName();
                    }
                };
                categoryColumn.setFieldUpdater(new FieldUpdater<Purchase, String>() {
                    @Override
                    public void update(int index, Purchase object, String value) {
                        selectedCategory.getPurchases().remove(object);
                        Category newCategory = null;
                        object.setId(null);
                        for (Category category : CATEGORIES) {
                            if (category.getName().equals(value)) {
                                newCategory = category;
                                if (newCategory.getPurchases() == null) {
                                    newCategory.setPurchases(new ArrayList<Purchase>());
                                }
                                newCategory.getPurchases().add(object);
                                break;
                            }
                        }
                        categoryRestService.update(selectedCategory.getId(), selectedCategory, VOID_UPDATE_CALLBACK);
                        categoryRestService.update(newCategory.getId(), newCategory, VOID_UPDATE_CALLBACK);
                    }
                });
                cellTable.addColumn(categoryColumn, "Category");
            }
        });

        Wallet.EVENT_BUS.addHandler(CategorySelectedEvent.TYPE, new CategorySelectedEvent.CategorySelectedEventHandler() {
            @Override
            public void onCategorySelected(CategorySelectedEvent сategorySelectedEvent) {
                //TODO possible NPE
                selectedCategory = сategorySelectedEvent.getCategory();
                if (selectedCategory != null && selectedCategory.getPurchases() != null) {
                    cellTable.setRowData((List<Purchase>) selectedCategory.getPurchases());
                }
                purchaseAdd.setCategory(selectedCategory);
            }
        });

        initWidget(uiBinder.createAndBindUi(this));
    }


    private static Collection<Category> CATEGORIES;
    private static List<String> CATEGORY_NAMES;
}
