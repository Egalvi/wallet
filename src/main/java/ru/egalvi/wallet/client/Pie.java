package ru.egalvi.wallet.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;
import ru.egalvi.wallet.client.crud.CategorySelectedEvent;
import ru.egalvi.wallet.shared.domain.Category;
import ru.egalvi.wallet.shared.domain.Purchase;

public class Pie extends Composite {
    interface PieUiBinder extends UiBinder<Widget, Pie> {
    }

    private static PieUiBinder ourUiBinder = GWT.create(PieUiBinder.class);

    @UiField
    HTMLPanel chartPanel;

    public Pie() {
        initWidget(ourUiBinder.createAndBindUi(this));
        buildChart();
    }


    private void buildChart() {
        Runnable onLoadCallback = new Runnable() {
            public void run() {
                Wallet.EVENT_BUS.addHandler(CategorySelectedEvent.TYPE, new CategorySelectedEvent.CategorySelectedEventHandler() {
                    @Override
                    public void onCategorySelected(CategorySelectedEvent сategorySelectedEvent) {
                        Category selectedCategory = сategorySelectedEvent.getCategory();
                        DataTable dataTable = createTable();
                        dataTable.addRows(selectedCategory.getSubCategories().size() + selectedCategory.getPurchases().size());
                        int i = 0;
                        for (Category category : selectedCategory.getSubCategories()){
                            dataTable.setValue(i, 0, category.getName());
                            dataTable.setValue(i, 1, category.sum());
                            ++i;
                        }
                        for (Purchase purchase : selectedCategory.getPurchases()){
                            dataTable.setValue(i, 0, purchase.getName());
                            dataTable.setValue(i, 1, purchase.getPrice());
                            ++i;
                        }
                        // Create a pie chart visualization.
                        PieChart pie = new PieChart(dataTable, createOptions());
                        pie.addSelectHandler(createSelectHandler(pie));
                        chartPanel.add(pie);
                    }
                });
            }
        };
        VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
    }

    private PieChart.Options createOptions() {
        PieChart.Options options = PieChart.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        options.set3D(true);
        options.setTitle("Amount by categories");
        return options;
    }

    private SelectHandler createSelectHandler(final PieChart chart) {
        return new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
            }
        };
    }

    private DataTable createTable() {
        DataTable dataTable = DataTable.create();
        dataTable = DataTable.create();
        dataTable.addColumn(AbstractDataTable.ColumnType.STRING, "Name");
        dataTable.addColumn(AbstractDataTable.ColumnType.NUMBER, "Amount");
        return dataTable;
    }
}