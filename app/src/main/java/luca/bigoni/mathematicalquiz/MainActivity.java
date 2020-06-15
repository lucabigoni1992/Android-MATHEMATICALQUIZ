package luca.bigoni.mathematicalquiz;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import luca.bigoni.mathematicalquiz.DataBase.DataBaseHandler;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    private AppBarConfiguration mAppBarConfiguration;
    private static InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_numeric_series, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.nav_contat_support)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        DataBaseHandler dbh = new DataBaseHandler(this);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
// Create the InterstitialAd and set the adUnitId.

        interstitialAd = new InterstitialAd(getApplicationContext());
        interstitialAd.setAdUnitId(AD_UNIT_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        // Defined in res/values/strings.xml
        Button btn = (Button) findViewById(R.id.provaPublicita);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (interstitialAd != null && interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }

                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        Log.d("TAG", " publictà onAdLoaded");
                        // Code to be executed when an ad finishes loading.
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        Log.d("TAG", " publictà onAdFailedToLoad");
                        // Code to be executed when an ad request fails.
                    }

                    @Override
                    public void onAdOpened() {
                        Log.d("TAG", " publictà onAdOpened");
                        // Code to be executed when the ad is displayed.
                    }

                    @Override
                    public void onAdClicked() {
                        Log.d("TAG", " publictà onAdClicked");
                        // Code to be executed when the user clicks on an ad.
                    }

                    @Override
                    public void onAdLeftApplication() {
                        Log.d("TAG", " publictà onAdLeftApplication");
                        // Code to be executed when the user has left the app.
                    }

                    @Override
                    public void onAdClosed() {
                        Log.d("TAG", " publictà onAdClosed");
                        AdRequest adRequest = new AdRequest.Builder().build();
                        interstitialAd.loadAd(adRequest);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
