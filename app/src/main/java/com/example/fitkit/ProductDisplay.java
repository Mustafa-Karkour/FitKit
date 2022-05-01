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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.collect.Sets;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class ProductDisplay extends AppCompatActivity {
    private static final String TAG = "P_READ";
    //FirebaseDatabase database;
    FirebaseFirestore db;
    String productID;
    String modelLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

        // productID = getIntent().getStringExtra("productID");
        productID = "e001";

        // Access a Cloud Firestore instance from the activity
        db = FirebaseFirestore.getInstance();

        // Fetches data about product using its ID
        DocumentReference docRef = db.collection("sports_equipment").document(productID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                // Save data in an object of type Product
                Product product = documentSnapshot.toObject(Product.class);

                Log.d(TAG, "Name: " + product.getName());
                Log.d(TAG, "Desc: " + product.getDesc());
                Log.d(TAG, "Price: " + product.getPrice());
                HashMap<String, String> hm = product.getImg_links();
                for(String i: hm.keySet()) {
                    Log.d(TAG, "Img Link: " + hm.get(i));
                }

                // Sets the text into the TextViews
                TextView pName = findViewById(R.id.productName);
                TextView pPrice = findViewById(R.id.price);
                TextView pDesc = findViewById(R.id.description);
                pName.setText(product.getName());
                pPrice.setText(String.format("$ %.2f", product.getPrice()));
                pDesc.setText(product.getDesc());

                // Use image url and display the product image using Glide
                ImageView imgView = findViewById(R.id.imageView);
                Glide.with(ProductDisplay.this)
                        .load(product.getImg_links().get("img1"))
                        .into(imgView);

                // Save link to 3D model in a variable
                modelLink = product.getModel();
            }
        });
    }

    public void toARModelViewer(View v) {
        Intent intent = new Intent(getApplicationContext(), ARModelViewer.class);
        intent.putExtra("modelLink", modelLink);
        startActivity(intent);
    }

    public void toMeasureActivity(View v){
        startActivity(new Intent(getApplicationContext(), MeasureActivity.class));
    }

}