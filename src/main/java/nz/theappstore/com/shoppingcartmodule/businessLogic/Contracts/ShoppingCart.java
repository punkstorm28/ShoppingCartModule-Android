package nz.theappstore.com.shoppingcartmodule.businessLogic.Contracts;

import rx.Completable;
import rx.Single;

/**
 *
 * Created by {@author vyomkeshjha} on 12/4/17.
 * {@link rx.Completable} because each of these operations on the local cart may lead to asynchronous operations
 * on the remote cart or persistent cart, which in turn may lead to exceptions.
 */
public interface ShoppingCart<T> {
    Single<Integer> decreaseItemQuantity(T item);
    Single<Integer> increaseItemQuantity(T item);

    Completable removeItemFromCart(T item);
    Completable addItemToCart(T item);
    T getItemFromCart(int position);

    int getProductQuantity(T product);
    int getNumberOfItemsInCart();
    int getNumberOfProductsInCart();

    Completable emptyCart();
}
