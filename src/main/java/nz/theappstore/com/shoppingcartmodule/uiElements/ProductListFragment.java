package nz.theappstore.com.shoppingcartmodule.uiElements;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartAbstractList;
import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartImpl;
import nz.theappstore.com.shoppingcartmodule.uiElements.adapters.ProductListAdapter;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewModels.ShoppingCartViewModel;
import rx.Observer;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Get product list data from the view model and work with it
 */
public class ProductListFragment extends Fragment {

    ShoppingCartViewModel cartViewModel;
    ProductListAdapter dataAdapter = new ProductListAdapter();
    ShoppingCartAbstractList itemList = new ShoppingCartImpl<SampleProductEntity>();  //FIXME: really?
    RecyclerView recyclerViewList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);
        //TODO: set up the observer on the data-set
        dataAdapter.setProductList(itemList);
        cartViewModel.getShoppingCart().getListOfProducts().subscribe(new Observer<SampleProductEntity>() {
            @Override
            public void onCompleted() { }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onNext(SampleProductEntity sampleProductEntity) {
                //TODO: when products change
                itemList.add(sampleProductEntity);
                dataAdapter.notifyDataSetChanged(); //refresh list
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listView = inflater.inflate(R.layout.product_list_fragment_layout, container, false);

        return listView;
    }
}
