package nz.theappstore.com.shoppingcartmodule.uiElements;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewModels.ShoppingCartViewModel;
import rx.subjects.PublishSubject;

public class ProductInfoFragment extends Fragment {

    ShoppingCartViewModel cartViewModel;
    SampleProductEntity currentProductEntity;
    PublishSubject<SampleProductEntity> addedToCart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);
        addedToCart = PublishSubject.create();
    }

    public void setupItemPipe(SampleProductEntity currentProductEntity) {
        this.currentProductEntity = currentProductEntity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.product_info_fragment, container, false);
        TextView productTitle = fragmentLayout.findViewById(R.id.product_name_textview);
        TextView productDescription = fragmentLayout.findViewById(R.id.product_description_textview);
        Button addToCartButton = fragmentLayout.findViewById(R.id.add_to_cart);

        productTitle.setText(currentProductEntity.getProductName());
        productDescription.setText(currentProductEntity.getProductDescription());
        addToCartButton.setOnClickListener(view -> {
            cartViewModel.getShoppingCart().addItemToCart(currentProductEntity);
        });

        return fragmentLayout;
    }
}
