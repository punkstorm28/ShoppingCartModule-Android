package nz.theappstore.com.shoppingcartmodule.uiElements.util;

import nz.theappstore.com.shoppingcartmodule.persistence.UserEntity;
import rx.subjects.ReplaySubject;

public class JobWorkerBus {

    private static ReplaySubject<UserEntity> subject = ReplaySubject.create();

    public static void setUser(UserEntity currentUser) {
        subject.onNext(currentUser);
    }

    public static ReplaySubject<UserEntity> getObservableWorker() {
        return subject;
    }
}
