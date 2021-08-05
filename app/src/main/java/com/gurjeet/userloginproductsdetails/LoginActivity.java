package com.gurjeet.userloginproductsdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    EditText username,password;
    Button login;
    public static ArrayList<Client> clients=new ArrayList<>();

    public static String UserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fillClients();


        username=findViewById(R.id.extUsername);
        password=findViewById(R.id.extPassword);
        login=findViewById(R.id.btnLogin);

        //here to check username and password using login button click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(verify(user,pass))
                {
                    UserName=user;
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    //now taking the person username with us to next page

                }
                else
                    Toast.makeText(getBaseContext(),"Username or Password Invalid", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void fillClients(){
        clients.add(new Client("Sergio Marqina","Street No. 1, Spain","user1","123456"));
        clients.add(new Client("Raquel Murillo","California, USA","user2","123456"));
        clients.add(new Client("Sheldon Cooper","Los angeles, California","user3","123456"));
    }

    // a method to verify the username and password
    public boolean verify(String user, String pass){
        for(Client cln:clients)
            if(cln.getUsername().equalsIgnoreCase(user) && cln.getPassword().equals(pass))
                return true;
            return false;
    }




}