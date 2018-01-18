package nz.theappstore.com.shoppingcartmodule.persistence.network.repository;



import java.util.List;

import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import rx.Observable;

/**
 * Created by vyomkeshjha on 10/22/17.
 */

public interface DataRepository {
    Observable<List<SampleProductEntity>> getProductsList();
}
