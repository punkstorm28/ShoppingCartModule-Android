package nz.theappstore.com.shoppingcartmodule.businessLogic;

import rx.Completable;
import rx.Single;

/**
 * Created by vyomkeshjha on 12/4/17.
 */

public class ShoppingCartImpl<T> extends ShoppingCartAbstractList<T> {
    @Override
    public Single<Integer> decreaseItemQuantity(T item) {
        return null;
    }

    @Override
    public Single<Integer> increaseItemQuantity(T item) {
        return null;
    }

    @Override
    public Completable removeItemFromCart(T item) {
        return null;
    }

    @Override
    public Completable addItemToCart(T item) {
        return null;
    }

    @Override
    public Single getItemFromCart(T item) {
        return null;
    }

    @Override
    public Single<Integer> getNumberOfItemsInCart() {
        return null;
    }

    @Override
    public Single<Integer> getNumberOfProductsInCart() {
        return null;
    }

    @Override
    public Completable emptyCart() {
        return null;
    }
}
