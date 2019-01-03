package comviewappisome.google.sites.watchandearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
