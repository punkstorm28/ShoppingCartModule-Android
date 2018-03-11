package nz.theappstore.com.shoppingcartmodule.uiElements.viewModels;

import android.arch.lifecycle.ViewModel;


import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartImpl;
import rx.Observable;

/**
 * Created by vyomkeshjha on 12/5/17.
 */

public class ShoppingCartViewModel extends ViewModel {
    private ShoppingCartImpl shoppingCart ;
    private Observable<ShoppingCartImpl> observableCart;
    private int sessionId;

    ShoppingCartViewModel() {

    }

    public ShoppingCartImpl getShoppingCart() {

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCartImpl();
            shoppingCart.setSessionIdAndSync(sessionId);
        }
        return shoppingCart;
    }

    public Observable<ShoppingCartImpl> getObservableShoppingCart(int sessionId) {
        if (observableCart == null)
            observableCart = shoppingCart.getObservable();
        return observableCart;  //FIXME: too drunk
    }


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

}
