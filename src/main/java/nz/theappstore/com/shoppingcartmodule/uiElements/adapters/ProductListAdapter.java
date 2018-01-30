package nz.theappstore.com.shoppingcartmodule.uiElements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewHolders.ProductItemViewHolder;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import rx.subjects.PublishSubject;

/**
 * Created by vyomkeshjha on 12/6/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductItemViewHolder> {

    private List<SampleProductEntity> productList = new ArrayList<>();
    final private PublishSubject<SampleProductEntity> currentSubject;

    public ProductListAdapter(List<SampleProductEntity> productList) {
        this.productList = productList;
        currentSubject = PublishSubject.create();
    }

    public ProductListAdapter() {
        currentSubject = PublishSubject.create();
    }

    public void setProductList(List<SampleProductEntity> productList) {
        this.productList.clear();
        this.productList.addAll(productList);
        notifyDataSetChanged();
    }

    @Override
    public ProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View group = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.product_item_in_product_list, parent, false);
        return new ProductItemViewHolder(group);
    }

    @Override
    public void onBindViewHolder(ProductItemViewHolder holder, int position) {
        SampleProductEntity productEntity = productList.get(position);
        holder.getProductName().setText(productEntity.getProductName());
        holder.getProductDescription().setText(productEntity.getProductDescription());
        //TODO: setup on click listener on the product item, pref open detailed description pane or add it to cart
        holder.getAddToCart().setOnClickListener(view -> {
            currentSubject.onNext(productList.get(position));
        });
    }

    public PublishSubject<SampleProductEntity> getCurrentSubject() {
        return currentSubject;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
