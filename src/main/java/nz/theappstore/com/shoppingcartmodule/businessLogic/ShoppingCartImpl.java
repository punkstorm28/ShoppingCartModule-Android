package nz.theappstore.com.shoppingcartmodule.businessLogic;

import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Reactive stream return types to ensure that the return happens only after server response
 * Change this to the user's requirements
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
    public T getItemFromCart(int position) {
        return get(position);
    }

    @Override
    public int getProductQuantity(T product) {
        return 0;
    }

    @Override
    public int getNumberOfItemsInCart() {
        return super.getNumberOfItemsInCart();
    }

    @Override
    public int getNumberOfProductsInCart() {
        return size();
    }

    @Override
    public Observable<T> getListOfProductsInCart() {
        return null;
    }

    @Override
    public Observable<T> getListOfProducts() {
        return null;
    }

    @Override
    public Completable emptyCart() {
        return null;
    }
}
