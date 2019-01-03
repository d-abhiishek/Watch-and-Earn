package comviewappisome.google.sites.watchandearn;

import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import rubikstudio.library.LuckyWheelView;
import rubikstudio.library.model.LuckyItem;

public class LuckyWheel extends AppCompatActivity {

    private Calendar calendar;
    private int weekday;
    private String todayString;
    List<LuckyItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_wheel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SharedPreferences coins = getSharedPreferences("Rewards", MODE_PRIVATE);

        final LuckyWheelView luckyWheelView = (LuckyWheelView) findViewById(R.id.luckyWheel);

        findViewById(R.id.play).setEnabled(true);
        findViewById(R.id.play).setAlpha(1f);

        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        weekday = calendar.get(Calendar.DAY_OF_WEEK);
        todayString = year + "" + month + "" + day;

        final SharedPreferences spinChecks = getSharedPreferences("SPINCHECK", 0);
        final boolean currentDay = spinChecks.getBoolean(todayString, false);

        LuckyItem luckyItem1 = new LuckyItem();
        luckyItem1.text = "10";
        //luckyItem1.icon = R.drawable.test1;
        luckyItem1.color = 0xffFFF3E0;
        data.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.text = "20";
        //luckyItem2.icon = R.drawable.test2;
        luckyItem2.color = 0xffFFE0B2;
        data.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.text = "30";
        //luckyItem3.icon = R.drawable.test3;
        luckyItem3.color = 0xffFFCC80;
        data.add(luckyItem3);

        //////////////////
        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.text = "40";
        //luckyItem4.icon = R.drawable.test4;
        luckyItem4.color = 0xffFFF3E0;
        data.add(luckyItem4);

        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.text = "50";
        //luckyItem5.icon = R.drawable.test5;
        luckyItem5.color = 0xffFFE0B2;
        data.add(luckyItem5);

        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.text = "60";
        //luckyItem6.icon = R.drawable.test6;
        luckyItem6.color = 0xffFFCC80;
        data.add(luckyItem6);
        //////////////////

        //////////////////////
        LuckyItem luckyItem7 = new LuckyItem();
        luckyItem7.text = "70";
        //luckyItem7.icon = R.drawable.test7;
        luckyItem7.color = 0xffFFF3E0;
        data.add(luckyItem7);

        LuckyItem luckyItem8 = new LuckyItem();
        luckyItem8.text = "80";
        //luckyItem8.icon = R.drawable.test8;
        luckyItem8.color = 0xffFFE0B2;
        data.add(luckyItem8);


        LuckyItem luckyItem9 = new LuckyItem();
        luckyItem9.text = "90";
        //luckyItem9.icon = R.drawable.test9;
        luckyItem9.color = 0xffFFCC80;
        data.add(luckyItem9);
        ////////////////////////

        LuckyItem luckyItem10 = new LuckyItem();
        luckyItem10.text = "100";
        //luckyItem10.icon = R.drawable.test10;
        luckyItem10.color = 0xffFFF3E0;
        data.add(luckyItem10);

        LuckyItem luckyItem11 = new LuckyItem();
        luckyItem11.text = "110";
        //luckyItem11.icon = R.drawable.test10;
        luckyItem11.color = 0xffFFE0B2;
        data.add(luckyItem11);

        LuckyItem luckyItem12 = new LuckyItem();
        luckyItem12.text = "120";
        //luckyItem12.icon = R.drawable.test10;
        luckyItem12.color = 0xffFFCC80;
        data.add(luckyItem12);

        /////////////////////

        luckyWheelView.setData(data);
        luckyWheelView.setRound(getRandomRound());

        /*luckyWheelView.setLuckyWheelBackgrouldColor(0xff0000ff);
        luckyWheelView.setLuckyWheelTextColor(0xffcc0000);
        luckyWheelView.setLuckyWheelCenterImage(getResources().getDrawable(R.drawable.icon));
        luckyWheelView.setLuckyWheelCursorImage(R.drawable.ic_cursor);*/


        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentDay){
                    int index = getRandomIndex();
                    luckyWheelView.startLuckyWheelWithTargetIndex(index);
                    SharedPreferences.Editor spins = spinChecks.edit();
                    spins.putBoolean(todayString, true);
                    spins.apply();
                    findViewById(R.id.play).setEnabled(false);
                    findViewById(R.id.play).setAlpha(.5f);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Come back tomorrow for free spin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                Toast.makeText(getApplicationContext(), String.valueOf(index*10), Toast.LENGTH_SHORT).show();
                int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
                coinCount = coinCount + (index*10);
                SharedPreferences.Editor coinsEdit = coins.edit();
                coinsEdit.putString("Coins", String.valueOf(coinCount));
                coinsEdit.apply();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("History");
                myRef.push().setValue("Lucky Spin - (+"+ (index*10) +" Coins)");
            }
        });
    }


    private int getRandomIndex() {
        //Random rand = new Random();
        //return rand.nextInt(data.size() - 1) + 0;
        int[] ind = new int[] {1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,5,5,5,6,6,7,7,8,9,10,11,12};
        int rand = new Random().nextInt(ind.length);
        return ind[rand];

    }

    private int getRandomRound() {
        Random rand = new Random();
        return rand.nextInt(10) + 15;
    }
}
