package nz.theappstore.com.shoppingcartmodule.persistence.network;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by vyomkeshjha on 10/22/17.
 */

public class AuthorizationInterceptor {

    public static OkHttpClient getInterceptedClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request originalRequest = chain.request();
            System.out.println(originalRequest.body());
            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                    Credentials.basic("Ram", "crimson"));

            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }).build();
        return okHttpClient;
    }
}
