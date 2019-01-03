package comviewappisome.google.sites.watchandearn;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RedeemAmazonPay extends AppCompatActivity {

    private Button checkOut;
    private TextView mobileno;
    private SharedPreferences coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_amazon_pay);

        coins = getSharedPreferences("Rewards", MODE_PRIVATE);

        checkOut = (Button) findViewById(R.id.paytmButton);
        mobileno = (TextView) findViewById(R.id.payTmmobile);

        int coinCount = Integer.parseInt(coins.getString("Coins", "0"));

        if (coinCount>= 100){
            checkOut.setEnabled(true);
            checkOut.setAlpha(1f);
        }
        else {
            checkOut.setEnabled(false);
            checkOut.setAlpha(.5f);
        }
    }

    public void checkout(View view) {
        if(mobileno.length() <= 9 || mobileno.length() >= 11){
            mobileno.setError("Enter a valid mobile no.");
        }
        else {
            String request = "AmazonPay-" + mobileno.getText().toString();
            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Payment");
            myRef.push().setValue(request);
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount - 100;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();

            if (coinCount>= 100){
                checkOut.setEnabled(true);
                checkOut.setAlpha(1f);
            }
            else {
                checkOut.setEnabled(false);
                checkOut.setAlpha(.5f);
            }

            Toast.makeText(this, "Your payment will be cleared within 3 days", Toast.LENGTH_SHORT).show();
            mobileno.setText("");

            DatabaseReference myRef1 = database.getReference("History");
            myRef1.push().setValue("Payment (via Amazon Pay) - (-10,000 Coins)");
        }
    }
}
