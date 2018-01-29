package nz.theappstore.com.shoppingcartmodule.businessLogic;

import java.util.List;

import nz.theappstore.com.shoppingcartmodule.persistence.network.RepositoryImpl;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import rx.Completable;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.subjects.PublishSubject;

/**
 * Created by vyomkeshjha on 12/4/17.
 * Reactive stream return types to ensure that the return happens only after server response
 * Change this to the user's requirements
 *
 * On a successful operation, return onAdd with the new cart
 */

public class ShoppingCartImpl extends ShoppingCartAbstractList<SampleProductEntity> {

    private final PublishSubject<ShoppingCartImpl> onAdd;  //To return the entire new list when any operation is performed
    RepositoryImpl repository;
    int sessionId;
    
    //TODO: pass the sessionId though the intent into the activity
    public ShoppingCartImpl() {
        this.onAdd = PublishSubject.create();
        this.repository = new RepositoryImpl();
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }


    @Override
    public Single<Integer> decreaseItemQuantity(SampleProductEntity item) {
        Observable<List<SampleProductEntity>> newList = repository.removeItemFromCart(sessionId, item.getProductId());
        //TODO: update the local instance
        newList.subscribe(new Observer<List<SampleProductEntity>>() {
            @Override
            public void onCompleted() {
                onAdd.onNext(ShoppingCartImpl.this);
                reduce(item);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SampleProductEntity> sampleProductEntities) {

            }
        });
        return Utils.convertObservableToSizedSingle(newList);
    }

    @Override
    public Single<Integer> increaseItemQuantity(SampleProductEntity item) {
        Observable<List<SampleProductEntity>> newList = repository.addItemToCart(sessionId, item.getProductId());
        newList.subscribe(new Observer<List<SampleProductEntity>>() {
            @Override
            public void onCompleted() {
                onAdd.onNext(ShoppingCartImpl.this);
                add(item);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SampleProductEntity> sampleProductEntities) {

            }
        });
        return  Utils.convertObservableToSizedSingle(newList);
    }

    @Override
    public Completable removeItemFromCart(SampleProductEntity item) {
        //TODO: implement it on the server side
        return null;
    }

    @Override
    public Completable addItemToCart(SampleProductEntity item) {
        Observable<List<SampleProductEntity>> newList = repository.addItemToCart(sessionId, item.getProductId());
        newList.subscribe(new Observer<List<SampleProductEntity>>() {
            @Override
            public void onCompleted() {
                onAdd.onNext(ShoppingCartImpl.this);
                add(item);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SampleProductEntity> sampleProductEntities) {

            }
        });
        return Utils.didOperationComplete(newList);
    }

    @Override
    public SampleProductEntity getItemFromCart(int position) {
        return get(position);
    }


    @Override
    public int getProductQuantity(SampleProductEntity product) {
        return getItemQuantity(product);
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
    public Observable<List<SampleProductEntity>> getListOfProductsInCart() {
        return null;  //FIXME: what?
    }

    @Override
    public Observable<List<SampleProductEntity>> getListOfProducts() {

        return repository.getProductsList();
    }


    @Override
    public Completable emptyCart() {
        return null;
    }

    public Observable<ShoppingCartImpl> getObservable() {
        return onAdd;
    }

}
