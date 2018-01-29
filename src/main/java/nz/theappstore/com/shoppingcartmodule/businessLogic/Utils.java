package nz.theappstore.com.shoppingcartmodule.businessLogic;

import java.util.List;

import rx.Completable;
import rx.Observable;
import rx.Observer;
import rx.Single;

public class Utils<T> {

    private static Single singleReturn;

    static Single convertObservableToSizedSingle(Observable from) {
        from.subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                singleReturn = Single.just(e);  //TODO: see if this throws an error or not
            }

            @Override
            public void onNext(Object o) {
                if(o instanceof List)
                singleReturn = Single.just(((List) o).size());
            }
        });
        return singleReturn;
    }


    static Completable didOperationComplete(Observable from) {
        return Completable.fromObservable(from);
    }

}
