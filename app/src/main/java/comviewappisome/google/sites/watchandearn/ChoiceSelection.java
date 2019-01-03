package comviewappisome.google.sites.watchandearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChoiceSelection extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;
    private Button interAds;
    private TextView coins2, test;
    public SharedPreferences coins;
    private String currentCoins;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coins = getSharedPreferences("Rewards", MODE_PRIVATE);

        currentCoins = coins.getString("Coins", "0");

        interAds = (Button) findViewById(R.id.WatchAds);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        coins2 = (TextView) headerView.findViewById(R.id.textViewCoins);
        //test = (TextView) findViewById(R.id.test);
        coins2.setText(currentCoins);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
                coinCount = coinCount + 2;
                SharedPreferences.Editor coinsEdit = coins.edit();
                coinsEdit.putString("Coins", String.valueOf(coinCount));
                coinsEdit.apply();
                coins2.setText(String.valueOf(coinCount));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("History");
                myRef.push().setValue("Opened Banner Ad - (+2 Coins)");
            }
        });

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        interAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
                coinCount = coinCount + 100;
                SharedPreferences.Editor coinsEdit = coins.edit();
                coinsEdit.putString("Coins", String.valueOf(coinCount));
                coinsEdit.apply();
                coins2.setText(String.valueOf(coinCount));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("History");
                myRef.push().setValue("Opened Small Ad - (+2 Coins)");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        final Handler handler = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
                coins2.setText(String.valueOf(coinCount));
                Log.d("Handlers", "Called on main thread");
                handler.postDelayed(this, 2000);
            }
        };
        handler.post(runnableCode);
    }

    private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()){
            mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.choice_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_aboutus) {
            Intent openAbout = new Intent(getApplicationContext(), About.class);
            startActivity(openAbout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inst) {
            Intent openInstructions = new Intent(getApplicationContext(), Instructions.class);
            startActivity(openInstructions);
        } else if (id == R.id.nav_redeem) {
            Intent openRedeem = new Intent(getApplicationContext(), Redeem.class);
            startActivity(openRedeem);
        } else if (id == R.id.nav_history) {
            Intent openHistory = new Intent(getApplicationContext(), History.class);
            startActivity(openHistory);
        } else if (id == R.id.nav_about) {
            Intent openAbout = new Intent(getApplicationContext(), About.class);
            startActivity(openAbout);
        } else if (id == R.id.nav_share) {
            Intent shareIt = new Intent(Intent.ACTION_SEND);
            shareIt.setType("text/plain");
            String body = "Hey there, try this new app to make easy money. Watch and earn is a free app which pays users just to watch ads. Not just that, it also gives you bonuses everyday. Click this link to download it - https://bit.ly/2R2risM";
            shareIt.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(shareIt, "Share using"));
        } else if (id == R.id.nav_rate) {
            Uri rate = Uri.parse("https://goo.gl/forms/2XAaT9Mlepp7rWqu2");
            Intent intent = new Intent(Intent.ACTION_VIEW, rate);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Intent contact = new Intent(Intent.ACTION_SENDTO);
            contact.setData(Uri.parse("mailto:appisomelabs@gmail.com"));
            startActivity(contact);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void startVideo(View view) {
        if(mRewardedVideoAd.isLoaded()){
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " + reward.getAmount(), Toast.LENGTH_SHORT).show();
        int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
        coinCount = coinCount + 50;
        SharedPreferences.Editor coinsEdit = coins.edit();
        coinsEdit.putString("Coins", String.valueOf(coinCount));
        coinsEdit.apply();
        coins2.setText(String.valueOf(coinCount));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("History");
        myRef.push().setValue("Watched Video Ad - (+50 Coins)");
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Toast.makeText(this, "onRewardedVideoAdLeftApplication",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        //Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        //Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //Toast.makeText(this, "Video ad Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        //Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    public void dailyCheck(View view) {
        Intent openDailyChecks = new Intent(getApplicationContext(), DailyCheckins.class);
        startActivity(openDailyChecks);
    }

    public void luckyWheel(View view) {
        Intent openLuckyWheel = new Intent(getApplicationContext(), LuckyWheel.class);
        startActivity(openLuckyWheel);
    }
}
