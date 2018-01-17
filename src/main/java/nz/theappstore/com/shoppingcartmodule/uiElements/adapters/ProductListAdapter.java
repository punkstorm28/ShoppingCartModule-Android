package nz.theappstore.com.shoppingcartmodule.uiElements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nz.theappstore.com.shoppingcartmodule.uiElements.viewHolders.ProductItemViewHolder;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;

/**
 * Created by vyomkeshjha on 12/6/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductItemViewHolder> {

    private List<SampleProductEntity> productList;

    public ProductListAdapter(List<SampleProductEntity> productList) {
        this.productList = productList;
    }

    public ProductListAdapter() {

    }

    public void setProductList(List<SampleProductEntity> productList) {
        this.productList = productList;
    }

    @Override
    public ProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductItemViewHolder(parent);  //FIXME: poor guy wrote this code drunk
    }

    @Override
    public void onBindViewHolder(ProductItemViewHolder holder, int position) {
        SampleProductEntity productEntity = productList.get(position);
        holder.getProductName().setText(productEntity.getProductName());
        holder.getProductDescription().setText(productEntity.getProductDescription());
        //TODO: setup on click listener on the product item, pref open detailed description pane or add it to cart
        holder.getAddToCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }


        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}
