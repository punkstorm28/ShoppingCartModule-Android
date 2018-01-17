package nz.theappstore.com.shoppingcartmodule.businessLogic;


import java.util.ArrayList;
import java.util.HashMap;

import nz.theappstore.com.shoppingcartmodule.businessLogic.Contracts.ShoppingCart;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Responsible for the local management of the cart's instance.
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

    @Override
    public boolean remove(Object o) {
        if (o != null && quantityMap.containsKey(o))
            quantityMap.remove(o);
        return super.remove(o);
    }

    /**
     * Empties the cart
     **/
    public boolean removeAll() {
        quantityMap.clear();
        return super.removeAll(this);
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
