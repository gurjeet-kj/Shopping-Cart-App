package com.gurjeet.userloginproductsdetails;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductsAdapter extends BaseAdapter{

    private ArrayList<Product> products;
    private LayoutInflater inflater;//we need this to link with the row_list.xml

    //constructor
    public ProductsAdapter(Context context, ArrayList<Product> products) {
        this.products = products;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //here we are creating the view
        ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.list_row,null); //here we linked listview of this class
            holder=new ViewHolder();

            holder.name=convertView.findViewById(R.id.txvProdName);
            holder.price=convertView.findViewById(R.id.txvProdPrice);
            holder.image=convertView.findViewById(R.id.ivsmallImage);
            convertView.setTag(holder);
        }else
            holder=(ViewHolder)convertView.getTag();
        holder.name.setText(products.get(position).getName());
        holder.price.setText(String.valueOf(products.get(position).getPrice()));
        holder.image.setImageResource(products.get(position).getImg());

        return convertView;
    }

    static class ViewHolder{
        //create attributes that match the components of list_row.xml
        private TextView name,price;
        private ImageView image;

    }


}
