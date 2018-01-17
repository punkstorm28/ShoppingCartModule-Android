package nz.theappstore.com.shoppingcartmodule.uiElements.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.CartListItemListener;

/**
 * Created by vyomkeshjha on 12/5/17.
 * Maps to the list item
 */
public class CartItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView productName;
    private final TextView productDescription;
    private final TextView currentProductQuantityTextView;
    private final TextView reduceProductCountTextView;
    private final TextView increaseProductCountTextView;
    private final TextView productRateTextView;
    private final TextView productPriceTextView;
    private final ImageView removeProductFromCart;

    private final CartListItemListener commonListener;

    public CartItemViewHolder(View itemView) {
        super(itemView);
        commonListener = new CartListItemListener();


        productName = itemView.findViewById(R.id.product_name_textview);
        productDescription = itemView.findViewById(R.id.product_description_textview);
        currentProductQuantityTextView = itemView.findViewById(R.id.current_product_quantity_textview);
        reduceProductCountTextView = itemView.findViewById(R.id.reduce_product_count_textview);
        increaseProductCountTextView = itemView.findViewById(R.id.increase_product_count_textview);
        productRateTextView = itemView.findViewById(R.id.product_rate_textview);
        productPriceTextView = itemView.findViewById(R.id.add_to_cart);
        removeProductFromCart = itemView.findViewById(R.id.delete_product_imageview);

        setupListener();
    }

    void setupListener() {
        itemView.setOnClickListener(commonListener);
        productName.setOnClickListener(commonListener);
        currentProductQuantityTextView.setOnClickListener(commonListener);
        reduceProductCountTextView.setOnClickListener(commonListener);
        increaseProductCountTextView.setOnClickListener(commonListener);
        productRateTextView.setOnClickListener(commonListener);
        productPriceTextView.setOnClickListener(commonListener);
        removeProductFromCart.setOnClickListener(commonListener);
    }

    public TextView getProductName() {
        return productName;
    }

    public TextView getProductDescription() {
        return productDescription;
    }

    public TextView getCurrentProductQuantityTextView() {
        return currentProductQuantityTextView;
    }

    public TextView getReduceProductCountTextView() {
        return reduceProductCountTextView;
    }

    public TextView getIncreaseProductCountTextView() {
        return increaseProductCountTextView;
    }

    public TextView getProductRateTextView() {
        return productRateTextView;
    }

    public TextView getProductPriceTextView() {
        return productPriceTextView;
    }

    public ImageView getRemoveProductFromCart() {
        return removeProductFromCart;
    }

}
