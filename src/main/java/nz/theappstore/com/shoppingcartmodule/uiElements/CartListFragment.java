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
import nz.theappstore.com.shoppingcartmodule.uiElements.adapters.CartListAdapter;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewModels.ShoppingCartViewModel;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vyomkeshjha on 12/6/17.
 */

public class CartListFragment extends Fragment {

    ShoppingCartViewModel cartViewModel;
    CartListAdapter dataAdapter = new CartListAdapter();
    ShoppingCartImpl itemList;

    RecyclerView cartList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);

        itemList = cartViewModel.getShoppingCart();
        dataAdapter.setDataset(itemList);

        cartViewModel.getShoppingCart()
                .getListOfProductsInCart()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
                        for (SampleProductEntity item: sampleProductEntities)
                        itemList.add(item);
                    }
                });
        handleListEvents();
    }
    public void setViewModelSessionId(int sessionId) {
        cartViewModel.setSessionId(sessionId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listView = inflater.inflate(R.layout.cart_list_fragment_layout, container, false);
        cartList = listView.findViewById(R.id.cart_list_recycler);
        cartList.setLayoutManager(new LinearLayoutManager(getContext()));

        cartList.setAdapter(dataAdapter);

        return listView;
    }

    void handleListEvents() {
        dataAdapter.getIncreaseQuantityPipe().subscribe(new Observer<SampleProductEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SampleProductEntity sampleProductEntity) {
                cartViewModel.getShoppingCart().increaseItemQuantity(sampleProductEntity);
            }
        });
        dataAdapter.getDecreaseQuantityPipe().subscribe(new Observer<SampleProductEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SampleProductEntity sampleProductEntity) {
                cartViewModel.getShoppingCart().decreaseItemQuantity(sampleProductEntity);
            }
        });
        dataAdapter.getRemoveItemPipe().subscribe(new Observer<SampleProductEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SampleProductEntity sampleProductEntity) {
                cartViewModel.getShoppingCart().removeItemFromCart(sampleProductEntity);
            }
        });
        itemList.getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShoppingCartImpl>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoppingCartImpl sampleProductEntities) {
                dataAdapter.notifyDataSetChanged();
            }
        });
        //TODO: handle error events
    }
}
