package com.gurjeet.userloginproductsdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private ArrayList<Cart> cartItems;
    private LayoutInflater inflater;//we need this to link with the row_list.xml

    //constructor
    public CartAdapter(Context context, ArrayList<Cart> cartItems) {
        this.cartItems = cartItems;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //here we are creating the view
        CartAdapter.ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.cart_row,null); //here we linked listview of this class
            holder=new CartAdapter.ViewHolder();

            holder.name=convertView.findViewById(R.id.txvCartItem);
            holder.qty=convertView.findViewById(R.id.txvCartQty);

            convertView.setTag(holder);
        }else
            holder=(CartAdapter.ViewHolder)convertView.getTag();
        holder.name.setText(cartItems.get(position).getProdName());
        holder.qty.setText(String.valueOf(cartItems.get(position).getQty()));

        return convertView;
    }

    static class ViewHolder{
        //create attributes that match the components of list_row.xml
        private TextView name,qty;


    }

}
