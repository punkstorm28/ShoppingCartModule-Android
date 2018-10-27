package nz.theappstore.com.shoppingcartmodule.uiElements.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nz.theappstore.com.shoppingcartmodule.R;

/**
 * Created by vyomkeshjha on 12/26/17.
 */

public class ProductItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView productName;
    private final TextView productDescription;
    private final TextView addToCart;

    public ProductItemViewHolder(View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.product_name_textview);
        productDescription = itemView.findViewById(R.id.product_description_textview);
        addToCart = itemView.findViewById(R.id.add_to_cart);
    }

    public TextView getProductName() {
        return productName;
    }

    public TextView getProductDescription() {
        return productDescription;
    }

    public TextView getAddToCart() {
        return addToCart;
    }
}
