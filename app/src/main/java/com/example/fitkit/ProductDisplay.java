package com.example.fitkit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProductDisplay extends AppCompatActivity {
    private static final String TAG = "P_READ";
    FirebaseDatabase database;
    String productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

//        Button btn = findViewById(R.id.btnViewInAR);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ARModelViewer.class));
//            }
//        });

        productID = "e001";

        // Get instance of Realtime Database and create reference based on ID
        database = FirebaseDatabase.getInstance();
        DatabaseReference productRef = database.getReference("products/sports_equipment/" + productID);

        // Fetches data about product, then listens for changes
        ValueEventListener productListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Creates a Product object with the retrieved values
                Product prod = dataSnapshot.getValue(Product.class);
                Log.d(TAG, "Name: " + prod.getName());
                Log.d(TAG, "Price: " + String.format("$ %.2f", prod.getPrice()));
                Log.d(TAG, "Description: " + prod.getDesc());
                Log.d(TAG, "Images: " + prod.getImg1());

                // Sets the text into the TextViews
                TextView pName = findViewById(R.id.productName);
                TextView pPrice = findViewById(R.id.price);
                TextView pDesc = findViewById(R.id.description);
                pName.setText(prod.getName());
                pPrice.setText(String.format("$ %.2f", prod.getPrice()));
                pDesc.setText(prod.getDesc());

                // Take url from database and display the product image using Glide
                ImageView imgView = findViewById(R.id.imageView);
                Glide.with(ProductDisplay.this)
                        .load(prod.getImg1())
                        .into(imgView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        productRef.addValueEventListener(productListener);
    }

    public void toARModelViewer(View v) {
        startActivity(new Intent(getApplicationContext(), ARModelViewer.class));
    }

    public void toMeasureActivity(View v){
        startActivity(new Intent(getApplicationContext(), MeasureActivity.class));
    }

}