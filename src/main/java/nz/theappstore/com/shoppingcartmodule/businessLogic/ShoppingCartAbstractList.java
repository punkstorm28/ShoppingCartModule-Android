package nz.theappstore.com.shoppingcartmodule.businessLogic;


import java.util.ArrayList;
import java.util.HashMap;

import nz.theappstore.com.shoppingcartmodule.businessLogic.Contracts.ShoppingCart;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Responsible for the local management of the cart's instance.
 * Can be subscribed on and the subscriber is notified with the list everytime the cart is updated
 */
public abstract class ShoppingCartAbstractList<T> extends ArrayList<T> implements ShoppingCart<T> {
    private HashMap<T, Integer> quantityMap = new HashMap<>();
    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            quantityMap.put(t, 1);
            return super.add(t);
        } else {
            quantityMap.put(t, quantityMap.get(t) + 1);
            return false;
        }
    }

    @Override
    public T remove(int index) {
        if (quantityMap.containsKey(get(index)))
            quantityMap.remove(get(index));
        return super.remove(index);
    }

    @Override //FIXME: remove is reduce in hashmap
    public boolean remove(Object o) {
        if (o != null && quantityMap.containsKey(o))
            quantityMap.remove(o);
        return super.remove(o);
    }

    /**
     * Reduce Item Quantity for an item in the cart
     **/
    public boolean reduce(T t) {
        if (!contains(t)) {
            return false;
        } else {
            int quantity = quantityMap.get(t) - 1;
            if(quantity == 0) {
                remove(t);
            } else {
                quantityMap.put(t, quantity);
                return true;
            }
        }
    return false;
    }

    /**
     * Empties the cart
     **/
    public boolean removeAll() {
        quantityMap.clear();
        boolean returnable = super.removeAll(this);
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

    /**
     * Use this method to observe on the Shopping cart
     * Returns the list onNext whenever anything changes
     **/
}
