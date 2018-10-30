package com.adnan.myresponsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by pc on 30/10/2018.
 */

public class SinopsisFilm extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button tampil = (Button) findViewById(R.id.btn_tampil);
        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SinopsisFilm.this, Jajal.class);
                startActivity(i);
            }
        });
    }
}
