package nz.theappstore.com.shoppingcartmodule.uiElements.viewModels;

import android.arch.lifecycle.ViewModel;

import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartImpl;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;

/**
 * Created by vyomkeshjha on 12/5/17.
 */

public class ShoppingCartViewModel extends ViewModel {
    private ShoppingCartImpl<SampleProductEntity> shoppingCart = new ShoppingCartImpl<>();

    public ShoppingCartImpl<SampleProductEntity> getShoppingCart() {
        return shoppingCart;
    }
}
