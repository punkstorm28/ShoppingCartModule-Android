package nz.theappstore.com.shoppingcartmodule.uiElements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.businessLogic.Contracts.ShoppingCart;
import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartImpl;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewHolders.CartItemViewHolder;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import rx.Completable;
import rx.subjects.PublishSubject;

/**
 * Created by vyomkeshjha on 12/5/17.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartItemViewHolder>{

    ShoppingCart<SampleProductEntity> dataset = new ShoppingCartImpl();
    PublishSubject<SampleProductEntity> increaseQuantityPipe;
    PublishSubject<SampleProductEntity> decreaseQuantityPipe;
    PublishSubject<SampleProductEntity> removeItemPipe;

    public CartListAdapter() {
        increaseQuantityPipe = PublishSubject.create();
        decreaseQuantityPipe = PublishSubject.create();
        removeItemPipe = PublishSubject.create();
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_in_cart, parent, false);

        return new CartItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, int position) {
        holder.getCurrentProductQuantityTextView().
                setText(String.valueOf(dataset.getProductQuantity(dataset.getItemFromCart(position))));
        holder.getProductDescription().
                setText(String.valueOf(dataset.getItemFromCart(position).getProductDescription()));
        holder.getProductName().
                setText(String.valueOf(dataset.getItemFromCart(position).getProductName()));


        holder.getIncreaseProductCountTextView().setOnClickListener(view -> {
            getIncreaseQuantityPipe().onNext(dataset.getItemFromCart(position));
        });

        holder.getReduceProductCountTextView().setOnClickListener(view -> {
            getDecreaseQuantityPipe().onNext(dataset.getItemFromCart(position));
        });

        holder.getRemoveProductFromCart().setOnClickListener(view -> {
            getRemoveItemPipe().onNext(dataset.getItemFromCart(position));
        });
        //TODO: setup event pipes
    }

    @Override
    public int getItemCount() {
        return dataset.getNumberOfItemsInCart();
    }

    public void setDataset(ShoppingCart<SampleProductEntity> dataset) {
        this.dataset = dataset;
    }

    public PublishSubject<SampleProductEntity> getIncreaseQuantityPipe() {
        return increaseQuantityPipe;
    }

    public PublishSubject<SampleProductEntity> getDecreaseQuantityPipe() {
        return decreaseQuantityPipe;
    }

    public PublishSubject<SampleProductEntity> getRemoveItemPipe() {
        return removeItemPipe;
    }
}
