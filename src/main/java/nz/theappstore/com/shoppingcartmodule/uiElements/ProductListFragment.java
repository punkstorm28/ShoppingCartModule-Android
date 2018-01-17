package nz.theappstore.com.shoppingcartmodule.uiElements;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.theappstore.com.shoppingcartmodule.uiElements.adapters.ProductListAdapter;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewModels.ShoppingCartViewModel;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Get product list data from the view model and work with it
 */
public class ProductListFragment extends ListFragment {

    ShoppingCartViewModel cartViewModel;
    ProductListAdapter dataAdapter = new ProductListAdapter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);
        //TODO: set up the observer on the data-set
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listView = super.onCreateView(inflater, container, savedInstanceState);

        return listView;
    }
}
