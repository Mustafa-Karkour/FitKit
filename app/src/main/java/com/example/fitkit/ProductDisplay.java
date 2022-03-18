package com.example.fitkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

        Button btn = findViewById(R.id.btnViewInAR);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ARModelViewer.class));
            }
        });
    }

    public void toMeasureActivity(View v){
        startActivity(new Intent(getApplicationContext(), MeasureActivity.class));
    }

}