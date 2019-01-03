package comviewappisome.google.sites.watchandearn;

import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DailyCheckins extends AppCompatActivity {

    boolean showedToday = false;
    private TextView coins2;
    private Calendar calendar;
    private int weekday;
    private SharedPreferences coins;
    private ImageButton sun, mon, tue, wed, thu, fri, sat;
    private String todayString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_checkins);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coins = getSharedPreferences("Rewards", MODE_PRIVATE);

        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        weekday = calendar.get(Calendar.DAY_OF_WEEK);
        todayString = year + "" + month + "" + day;

        sun = (ImageButton) findViewById(R.id.imgSun);
        mon = (ImageButton) findViewById(R.id.imgMon);
        tue = (ImageButton) findViewById(R.id.imgTue);
        wed = (ImageButton) findViewById(R.id.imgWed);
        thu = (ImageButton) findViewById(R.id.imgThu);
        fri = (ImageButton) findViewById(R.id.imgFri);
        sat = (ImageButton) findViewById(R.id.imgSat);

        sun.setEnabled(false);
        sun.setAlpha(.5f);
        mon.setEnabled(false);
        mon.setAlpha(.5f);
        tue.setEnabled(false);
        tue.setAlpha(.5f);
        wed.setEnabled(false);
        wed.setAlpha(.5f);
        thu.setEnabled(false);
        thu.setAlpha(.5f);
        fri.setEnabled(false);
        fri.setAlpha(.5f);
        sat.setEnabled(false);
        sat.setAlpha(.5f);

        if (weekday==1){
            sun.setEnabled(true);
            sun.setAlpha(1f);
        }
        else if (weekday==2){
            mon.setEnabled(true);
            mon.setAlpha(1f);
        }
        else if (weekday==3){
            tue.setEnabled(true);
            tue.setAlpha(1f);
        }
        else if (weekday==4){
            wed.setEnabled(true);
            wed.setAlpha(1f);
        }
        else if (weekday==5){
            thu.setEnabled(true);
            thu.setAlpha(1f);
        }
        else if (weekday==6){
            fri.setEnabled(true);
            fri.setAlpha(1f);
        }
        else if (weekday==7){
            sat.setEnabled(true);
            sat.setAlpha(1f);
        }
    }

    public void monCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);

        if (!currentDay){
            Toast.makeText(this, "10 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 10;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            myRef.push().setValue("Monday Daily Checkin - (+10 Coins)");
        }
        else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }

    public void tueCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);

        if (!currentDay){
            Toast.makeText(this, "10 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 10;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            myRef.push().setValue("Tuesday Daily Checkin - (+10 Coins)");
        }
        else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }

    public void wedCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);

        if (!currentDay){
            Toast.makeText(this, "20 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 20;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            myRef.push().setValue("Wednesday Daily Checkin - (+20 Coins)");
        }
        else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }

    public void thuCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);

        if (!currentDay){
            Toast.makeText(this, "20 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 20;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            myRef.push().setValue("Thursday Daily Checkin - (+20 Coins)");
        }
        else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }

    public void friCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);

        if (!currentDay){
            Toast.makeText(this, "30 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 30;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            myRef.push().setValue("Friday Daily Checkin - (+30 Coins)");
        }
        else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }

    public void satCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);

        if (!currentDay){
            Toast.makeText(this, "30 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 30;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            myRef.push().setValue("Saturday Daily Checkin - (+30 Coins)");
        }
        else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }

    public void sunCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);

        if (!currentDay){
            Toast.makeText(this, "50 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 50;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            myRef.push().setValue("Sunday Daily Checkin - (+50 Coins)");
        }
        else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }
}
