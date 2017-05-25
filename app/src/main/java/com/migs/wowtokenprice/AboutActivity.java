package com.migs.wowtokenprice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by Miguel on 5/23/2017.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tokenUrl = (TextView) findViewById(R.id.token_url);
        tokenUrl.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
