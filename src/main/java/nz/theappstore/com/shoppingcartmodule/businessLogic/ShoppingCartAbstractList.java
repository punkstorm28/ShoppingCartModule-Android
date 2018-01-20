package nz.theappstore.com.shoppingcartmodule.businessLogic;


import java.util.ArrayList;
import java.util.HashMap;

import nz.theappstore.com.shoppingcartmodule.businessLogic.Contracts.ShoppingCart;
import rx.subjects.PublishSubject;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Responsible for the local management of the cart's instance.
 * Can be subscribed on and the subscriber is notified with the list everytime the cart is updated
 */
public abstract class ShoppingCartAbstractList<T> extends ArrayList<T> implements ShoppingCart<T> {
    private HashMap<T, Integer> quantityMap = new HashMap<>();
    protected final PublishSubject<ShoppingCartAbstractList<T>> onAdd;  //To return the entire new list when any operation is performed

    ShoppingCartAbstractList() {
        onAdd = PublishSubject.create();
    }
    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            quantityMap.put(t, 1);
            onAdd.onNext(this);  //broadcast the new list to the subscribers
            return super.add(t);
        } else {
            quantityMap.put(t, quantityMap.get(t) + 1);
            onAdd.onNext(this);  //broadcast the new list to the subscribers
            return false;
        }
    }

    @Override
    public T remove(int index) {
        if (quantityMap.containsKey(get(index)))
            quantityMap.remove(get(index));
        onAdd.onNext(this);  //broadcast the new list to the subscribers
        return super.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        if (o != null && quantityMap.containsKey(o))
            quantityMap.remove(o);
        onAdd.onNext(this);  //broadcast the new list to the subscribers
        return super.remove(o);
    }

    /**
     * Empties the cart
     **/
    public boolean removeAll() {
        quantityMap.clear();
        boolean returnable = super.removeAll(this);
        onAdd.onNext(this);  //broadcast the new list to the subscribers
        return returnable;
    }

    public int getItemQuantity(T item) {
        if(contains(item)) {
            return quantityMap.get(item);
        } else {
            return 0;
        }
    }

    public int getNumberOfItemsInCart() {
        return quantityMap.size();
    }
}
