package nz.theappstore.com.shoppingcartmodule.uiElements;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartImpl;
import nz.theappstore.com.shoppingcartmodule.uiElements.adapters.ProductListAdapter;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewModels.ShoppingCartViewModel;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Get product list data from the view model and work with it
 */
public class ProductListFragment extends Fragment {

    ShoppingCartViewModel cartViewModel;
    ProductListAdapter dataAdapter = new ProductListAdapter();
    ShoppingCartImpl itemList = new ShoppingCartImpl();


    RecyclerView recyclerViewList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: set up the observer on the data-set
        cartViewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);
        itemList.setSessionId(cartViewModel.getSessionId());
        dataAdapter.setProductList(itemList);
    }

    @Override
    public void onStart() {
        super.onStart();
        setupCartListeners();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listView = inflater.inflate(R.layout.product_list_fragment_layout, container, false);
        recyclerViewList = listView.findViewById(R.id.product_list_recycler);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewList.setAdapter(dataAdapter);

        return listView;
    }

    public PublishSubject pipeProductSelectionEventToActivity() {
         return dataAdapter.getCurrentSubject();
    }

    void setupCartListeners() {
        cartViewModel.getShoppingCart().getObservable().subscribe(new Observer<ShoppingCartImpl>() {
            @Override
            public void onCompleted() {
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoppingCartImpl cartImpl) {
                dataAdapter.setProductList(cartImpl);  //FIXME: type match?
            }
        });

        cartViewModel.getShoppingCart()
                .getListOfProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SampleProductEntity>>() {
            @Override
            public void onCompleted() {
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SampleProductEntity> sampleProductEntities) {
                dataAdapter.setProductList(sampleProductEntities);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
