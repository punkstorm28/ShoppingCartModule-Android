package nz.theappstore.com.shoppingcartmodule.api_test;

import org.junit.Test;

import java.util.List;

import nz.theappstore.com.shoppingcartmodule.businessLogic.Contracts.ShoppingCart;
import nz.theappstore.com.shoppingcartmodule.businessLogic.ShoppingCartImpl;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import rx.Observer;

public class CartManagementRequestTests {


    @Test
    public void getCartFromServer() {
        ShoppingCart cart =  new ShoppingCartImpl();
        cart.getListOfProductsInCart().subscribe(new Observer<List<SampleProductEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SampleProductEntity> sampleProductEntities) {
                System.out.println(sampleProductEntities);
            }

        });
    }
}
