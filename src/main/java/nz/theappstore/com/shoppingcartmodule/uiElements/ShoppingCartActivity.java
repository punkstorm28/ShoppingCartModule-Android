package nz.theappstore.com.shoppingcartmodule.uiElements;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nz.theappstore.com.shoppingcartmodule.R;
import nz.theappstore.com.shoppingcartmodule.persistence.UserEntity;
import nz.theappstore.com.shoppingcartmodule.uiElements.viewModels.ShoppingCartViewModel;

public class ShoppingCartActivity extends AppCompatActivity {

    ShoppingCartViewModel viewModel ;
    private int sessionId;
    private UserEntity cartUser;
    Intent passedIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel.class);
        passedIntent = getIntent();
        sessionId = passedIntent.getIntExtra(getString(R.string.current_session_id),-1);
        viewModel.setSessionId(sessionId);


        attachProductsListFragment();


    }

    void attachProductsListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProductListFragment fragment = new ProductListFragment();
        fragmentTransaction.add(R.id.shopping_cart_fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
