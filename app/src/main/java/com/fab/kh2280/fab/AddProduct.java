package com.fab.kh2280.fab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddProduct extends AppCompatActivity {

    DatabaseReference myRef;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Write a message to the database
        product = new Product();
        myRef = FirebaseDatabase.getInstance().getReference().child("Product");


        product.setName("Nike Shoes");
        product.setPrice("1490");
        product.setDescription("Awesome shoes. Just Do it");
        product.setType("Shoes");
        product.setImage("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");
        myRef.child("Shoes").push().setValue(product);

        //Intent dummy logic
        Intent i = new Intent(this.getApplicationContext(),ProductList.class);
        startActivity(i);


    }
}