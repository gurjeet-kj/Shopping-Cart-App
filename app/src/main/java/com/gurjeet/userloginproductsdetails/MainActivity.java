package com.gurjeet.userloginproductsdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView welcome;
    ListView prodList;
    Button logout;
    public static ArrayList<Product> products=new ArrayList<>();
    public static int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        products.clear();

        welcome=findViewById(R.id.txvWelcome);
        prodList=findViewById(R.id.lvProducts);
        logout=findViewById(R.id.btnLogOut);

        String clientName=getClient(LoginActivity.UserName,LoginActivity.clients);
        welcome.setText("Welcome "+clientName);
        prodList.setAdapter(new ProductsAdapter(this,products));
        fillProduct();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        prodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index=position;
                Intent intent=new Intent(MainActivity.this, DescriptionActivity.class);
                startActivity(intent);
            }
        });

    }

   //method receives username and returns the client name
    public String getClient(String user,ArrayList<Client>clnts){
        for(Client cln:clnts)
            if(cln.getUsername().equalsIgnoreCase(user))
                return cln.getName();
            return null;
    }

    public void fillProduct(){
        products.add(new Product("Macbook Pro",5000.00,R.drawable.macbook,"MacBook Air is completely transformed by the power of Apple-designed M1 chip. Up to 3.5x faster CPU, 5x faster graphics and 18 hours of battery life."));
        products.add(new Product("Dell Inspiron",2000.00,R.drawable.dell,"INSPIRON 7000 · POWERFUL DESKTOPS · DELL SUPPORT · NEW ALIENWARE M15 R5 · PREMIUM SERVERS ·"));
        products.add(new Product("HP Laptop",1000.00,R.drawable.hpp,"HP 14s-DQ2101TU Laptop (11th Gen Intel Core i3-1115G4/8GB/256GB PCIe-SSD/Intel UHD"));
        products.add(new Product("Cooling Pad",100.00,R.drawable.coolingpad,"The metal mesh surface of Tarkan dual-fan cooling pad provides support for various laptops that come in sizes up to 15.6-inch"));
    }

}