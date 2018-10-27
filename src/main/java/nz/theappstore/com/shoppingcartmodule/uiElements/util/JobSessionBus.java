package nz.theappstore.com.shoppingcartmodule.uiElements.util;

import rx.subjects.ReplaySubject;

public class JobSessionBus {
    private static ReplaySubject<JobSessionLocal> subject = ReplaySubject.create();

    public static void setSession(JobSessionLocal localSession) {
        subject.onNext(localSession);
    }

    public static ReplaySubject<JobSessionLocal> getSessionObservable() {
        return subject;
    }

}
