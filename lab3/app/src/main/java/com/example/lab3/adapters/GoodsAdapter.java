package com.example.lab3.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.models.Good;
import com.example.lab3.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "GoodsAdapter";
    private Context context;
    private ArrayList<Good> arr_goods_adapter;
    private LayoutInflater layoutInflater;
    private TextView checkedGoodsCountTextView;
    private int checkedGoodsCount = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView textId, textHolder, priceHolder;
        final CheckBox checkBox;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textId = (TextView) v.findViewById(R.id.idPlaseholder);
            textHolder  = (TextView) v.findViewById(R.id.namePlaceholder);
            priceHolder  = (TextView) v.findViewById(R.id.priceHolder);
            checkBox = (CheckBox) v.findViewById(R.id.checkBox);

        }
    }
    public GoodsAdapter(Context context, ArrayList<Good> arr_goods_adapter, TextView checkedGoodsCountTextView) {
        this.context = context;
        this.arr_goods_adapter = arr_goods_adapter;
        this.layoutInflater = LayoutInflater.from(context);
        this.checkedGoodsCountTextView = checkedGoodsCountTextView;

            }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_good, viewGroup, false);

        this.checkedGoodsCount = getCheckedGoods().size();
        this.checkedGoodsCountTextView.setText("cart goods count: "+this.checkedGoodsCount);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textId.setText(Integer.toString(arr_goods_adapter.get(position).getId()) );
        holder.textHolder.setText(arr_goods_adapter.get(position).getName());
        holder.priceHolder.setText(arr_goods_adapter.get(position).getPrice());
        holder.checkBox.setChecked(arr_goods_adapter.get(position).getCheck());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public int getItemCount() {
        return this.arr_goods_adapter.size();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (compoundButton.isShown()) {
            int i = (int) compoundButton.getTag();
            arr_goods_adapter.get(i).setCheck(isChecked);
            notifyItemChanged(i);

            if (isChecked) {++this.checkedGoodsCount; } else {--this.checkedGoodsCount; };
            this.checkedGoodsCountTextView.setText("cart goods count: "+this.checkedGoodsCount);
        }
    }

    public ArrayList<Good> getCheckedGoods() {
        ArrayList<Good> arr_checked_goods = new ArrayList<>();

//        for ( Good elem : this.arr_goods_adapter){
//            if (elem.getCheck()) arr_checked_goods.add(elem);
//        }

        arr_checked_goods=(ArrayList<Good>) arr_goods_adapter.stream().filter(el -> el.getCheck()==true).collect(Collectors.toList());
        Stream.of(arr_checked_goods).forEach(System.out::println);
        return arr_checked_goods;
    }

    public int getCount() {
        return arr_goods_adapter.size();
    }
}
