package comviewappisome.google.sites.watchandearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Redeem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void payTm(View view) {
        Intent openPayTm = new Intent(getApplicationContext(), RedeemPayTm.class);
        startActivity(openPayTm);
    }
    public void phonePe(View view) {
        Intent openPhonePe = new Intent(getApplicationContext(), RedeemPhonePe.class);
        startActivity(openPhonePe);
    }
    public void gPay(View view) {
        Intent openGPay = new Intent(getApplicationContext(), RedeemGPay.class);
        startActivity(openGPay);
    }
    public void amazonPay(View view) {
        Intent openAmazonPay = new Intent(getApplicationContext(), RedeemAmazonPay.class);
        startActivity(openAmazonPay);
    }

}
