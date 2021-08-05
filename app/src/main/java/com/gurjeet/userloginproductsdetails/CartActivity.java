package com.gurjeet.userloginproductsdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class CartActivity extends AppCompatActivity {

    Button back;
    ListView cartList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        back=findViewById(R.id.btnBack2);
        cartList=findViewById(R.id.lvCart);

        cartList.setAdapter(new CartAdapter(this,DescriptionActivity.cartItems));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartActivity.this,DescriptionActivity.class);
                startActivity(intent);
            }
        });

        //remove item when click it
        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DescriptionActivity.cartItems.remove(position);
                cartList.setAdapter(new CartAdapter(CartActivity.this,DescriptionActivity.cartItems));
                //re-calculate when delete any product
                DescriptionActivity.totalAmount=DescriptionActivity.calcTotal();
            }
        });

    }



}