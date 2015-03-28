package ru.egalvi.wallet.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import ru.egalvi.wallet.client.crud.PurchasesViewModel;

import java.util.ArrayList;

public class Wallet implements EntryPoint {

    public void onModuleLoad() {
        RootPanel.get().add(GWT.<Widget>create(Layout.class));
    }

//    /**
//     * The model that defines the nodes in the tree.
//     */
//    private static class CustomTreeModel implements TreeViewModel {
//
//        /**
//         * Get the {@link NodeInfo} that provides the children of the specified
//         * value.
//         */
//        public <T> NodeInfo<?> getNodeInfo(T value) {
//      /*
//       * Create some data in a data provider. Use the parent value as a prefix
//       * for the next level.
//       */
//            ListDataProvider<String> dataProvider = new ListDataProvider<String>();
//            for (int i = 0; i < 2; i++) {
//                dataProvider.getList().add(value + "." + String.valueOf(i));
//            }
//
//            // Return a node info that pairs the data with a cell.
//            return new DefaultNodeInfo<String>(dataProvider, new TextCell());
//        }
//
//        /**
//         * Check if the specified value represents a leaf node. Leaf nodes cannot be
//         * opened.
//         */
//        public boolean isLeaf(Object value) {
//            // The maximum length of a value is ten characters.
//            return value.toString().length() > 10;
//        }
//    }
//
//    public void onModuleLoad() {
//        // Create a model for the browser.
//        TreeViewModel model = new CustomTreeModel();
//
//    /*
//     * Create the browser using the model. We specify the default value of the
//     * hidden root node as "Item 1".
//     */
//        CellBrowser tree = new CellBrowser.Builder<String>(new PurchasesViewModel(), "Item 1").build();
//
//        // Add the tree to the root layout panel.
//        RootLayoutPanel.get().add(tree);
////        RootPanel.get("test").add(tree);
//    }
}