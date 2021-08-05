package com.gurjeet.userloginproductsdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {

    Button back,add,cart,place;
    ImageView bigImage;
    TextView prodDesc,title,qty,amount;

    SeekBar sb;
    public static ArrayList<Cart>cartItems=new ArrayList<>();

    public static double totalAmount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        back=findViewById(R.id.btnBack);
        bigImage=findViewById(R.id.ivBigimage);
        prodDesc=findViewById(R.id.txvDesc);
        title=findViewById(R.id.txvProdTitle);
        sb=findViewById(R.id.seekBar);
        add=findViewById(R.id.btnAdd);
        qty=findViewById(R.id.txvQty);
        amount=findViewById(R.id.txvTotalAmount);
        amount.setText(String.format("%.2f",totalAmount));
        cart=findViewById(R.id.btnCart);
        place=findViewById(R.id.btnPlace);

        title.setText(MainActivity.products.get(MainActivity.index).getName());
        bigImage.setImageResource(MainActivity.products.get(MainActivity.index).getImg());
        prodDesc.setText(MainActivity.products.get(MainActivity.index).getDescription());

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qty.setText(String.valueOf(sb.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=title.getText().toString();
                double price=MainActivity.products.get(MainActivity.index).getPrice();
                int currentQty=Integer.parseInt(qty.getText().toString());
                double total=price*currentQty*1.13;
                //check if item already exit in cart then simply add the qty only in exiting item
                Cart oldOb=findItem(name);
                if(oldOb==null) {
                    cartItems.add(new Cart(name, currentQty, total));

                    totalAmount = calcTotal();
                    amount.setText(String.format("%.2f", totalAmount));
                }else{
                    //change the qty only
                    int newQty=oldOb.getQty()+currentQty;
                    double newTotal=price*newQty*1.13; //here 1.13 is tax amount, automatically added
                    Cart newObj=new Cart(name,newQty,newTotal);
                    cartItems.remove(oldOb);
                    cartItems.add(newObj);
                    //display the recalculated price
                    totalAmount = calcTotal();
                    amount.setText(String.format("%.2f", totalAmount));
                }
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DescriptionActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address=findAddress(LoginActivity.UserName);
                String msg="Thank you, your total is "+String.format("%.2f", totalAmount)+", all products will be shipped to "+address;
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DescriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //method to calculate and return the total of totals in the cart
    public static double calcTotal(){
        double sum=0;
        for(Cart  crt:cartItems)
            sum+=crt.getTotal();
        return sum;
    }

    //method receives the item name and check if exist in the array list then return its quantity
    public Cart findItem(String name){
        for(Cart crt:cartItems)
            if(crt.getProdName().equals(name))
                return crt;
        return null;
    }
    // find address by username
    public String findAddress(String userName){
        for(Client cln:LoginActivity.clients)
            if (cln.getUsername().equals(LoginActivity.UserName))
                return cln.getAddress();
            return "";

    }



}