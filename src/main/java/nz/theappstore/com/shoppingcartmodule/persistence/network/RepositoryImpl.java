package nz.theappstore.com.shoppingcartmodule.persistence.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import nz.theappstore.com.shoppingcartmodule.persistence.network.repository.DataRepository;
import nz.theappstore.com.shoppingcartmodule.persistence.network.retrofitServiceInterfaces.CartManagementRequests;
import nz.theappstore.com.shoppingcartmodule.persistence.network.retrofitServiceInterfaces.UserAuthorizationRequests;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by vyomkeshjha on 10/22/17.
 */

public class RepositoryImpl implements DataRepository {
    private static final String LOG_TAG = "Repository";
    private static final String URL = "http://192.168.0.3:8080/";
    private Retrofit retrofit;
    private UserAuthorizationRequests userAuthorizationRequests;
    CartManagementRequests cartManagementRequests;

    public RepositoryImpl() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-DD-MM'T'HH:mm:ss")
                .setLenient().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                    @Override
                    public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                            throws JsonParseException {
                        try {
                            System.out.println("date parser :" + json.getAsString());
                            String dateString = json.getAsString();
                            long time = df.parse(dateString).getTime();
                            return new Date(time);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                })
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(AuthorizationInterceptor.getInterceptedClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //TODO: initialize request makers
        cartManagementRequests= retrofit.create(CartManagementRequests.class);
        userAuthorizationRequests = retrofit.create(UserAuthorizationRequests.class);
    }



    @Override
    public Observable<List<SampleProductEntity>> getProductsList() {
        final Observable<List<SampleProductEntity>> productLiveData = null;

        Call<List<SampleProductEntity>> leadCall = cartManagementRequests.getProductList();
        leadCall.enqueue(new Callback<List<SampleProductEntity>>() {
            @Override
            public void onResponse(Call<List<SampleProductEntity>> call, Response<List<SampleProductEntity>> response) {
                //TODO: insert into the stream returned
            }

            @Override
            public void onFailure(Call<List<SampleProductEntity>> call, Throwable t) {

            }
        });
        return productLiveData;
    }

}