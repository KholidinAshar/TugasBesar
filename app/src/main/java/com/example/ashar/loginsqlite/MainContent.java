package com.example.ashar.loginsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class MainContent extends AppCompatActivity {

//    CardView card;
    // mendeklarasi textview
    TextView text1;
    TextView text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

//        card = (CardView) findViewById(R.id.laundry1);
//        card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
//            }
//        });

        text1 = (TextView) findViewById(R.id.link1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DeskripsiContent2.class);
                startActivity(i);
            }
        });

        text2 = (TextView) findViewById(R.id.link2);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DeskripsiContent.class);
                startActivity(i);
            }
        });
    }
}
