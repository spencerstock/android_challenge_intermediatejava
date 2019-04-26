package com.example.intermediatejavachallenge;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button          button;
    DoubleClickView doubleClickView;
    Context         context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        button = findViewById(R.id.button);
        doubleClickView = findViewById(R.id.double_button);

        doubleClickView.SetOnClickListener(new DoubleClickListener() {
            @Override
            public void OnDoubleClick() {
                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
