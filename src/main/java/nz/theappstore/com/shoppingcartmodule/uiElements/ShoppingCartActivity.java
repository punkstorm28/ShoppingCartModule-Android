package nz.theappstore.com.shoppingcartmodule.uiElements;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.persistence.UserEntity;
import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewModels.ShoppingCartViewModel;
import rx.Observer;

public class ShoppingCartActivity extends AppCompatActivity {

    ShoppingCartViewModel viewModel ;
    private int sessionId;
    private UserEntity cartUser;   //TODO: pass this in the intent form the caller
    Intent passedIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel.class);
        passedIntent = getIntent();
        sessionId = passedIntent.getIntExtra(getString(R.string.current_session_id),-1);
        viewModel.setSessionId(sessionId);

        attachProductsListFragment();
    }



    void attachProductsListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for(Fragment fragment:fragmentManager.getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        ProductListFragment fragment = new ProductListFragment();

        fragmentTransaction.add(R.id.shopping_cart_fragment_container, fragment);
        fragmentTransaction.commit();
        fragment.pipeProductSelectionEventToActivity().subscribe(new Observer<SampleProductEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SampleProductEntity sampleProductEntity) {
                attachProductInfoFragment(sampleProductEntity);
            }

        });
    }

    void attachCartFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for(Fragment fragment:fragmentManager.getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        CartListFragment fragment = new CartListFragment();
        fragment.setViewModelSessionId(sessionId);
        fragmentTransaction.add(R.id.shopping_cart_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    void attachProductInfoFragment(SampleProductEntity currentProductEntity) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for(Fragment fragment:fragmentManager.getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        ProductInfoFragment fragment = new ProductInfoFragment();
        fragment.setupItemPipe(currentProductEntity);
        fragmentTransaction.add(R.id.shopping_cart_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                int i = item.getItemId();
                if (i == R.id.navigation_block_select) {
                    attachProductsListFragment();
                    return true;
                } else if (i == R.id.preferences) {
                    attachCartFragment();
                    return true;
                }
                return false;
            };
}
