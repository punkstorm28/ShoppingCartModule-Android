package nz.theappstore.com.shoppingcartmodule.uiElements.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.businessLogic.Contracts.ShoppingCart;
import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartImpl;
import nz.theappstore.com.shoppingcartmodule.uiElements.ProductItemViewHolder;

/**
 * Created by vyomkeshjha on 12/5/17.
 */

public class CartListAdapter extends RecyclerView.Adapter<ProductItemViewHolder>{

    ShoppingCart<SampleProductEntity> dataset = new ShoppingCartImpl<>();

    public CartListAdapter(List<SampleProductEntity> datasetParam) {
        for (SampleProductEntity productEntity: datasetParam) {
            dataset.addItemToCart(productEntity); //Adds products to the cart
        }
    }

    @Override
    public ProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        return new ProductItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductItemViewHolder holder, int position) {
        //TODO: here is where you take care of the showing part

        holder.getCurrentProductQuantityTextView().
                setText(dataset.getProductQuantity(dataset.getItemFromCart(position)));
    }

    @Override
    public int getItemCount() {
        return dataset.getNumberOfItemsInCart();
    }

    public void setDataset(ShoppingCart<SampleProductEntity> dataset) {
        this.dataset = dataset;
    }
}
