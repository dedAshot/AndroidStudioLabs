package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lab3.adapters.GoodsAdapter;
import com.example.lab3.models.Good;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static final int LIST_CODE_REQUEST=45;
    private TextView checkedGoodsCountTextView;
    private int checkedGoodsCount=0;
    private Button btnShow;
    private RecyclerView mailnRecyclerView;
    private ArrayList<Good> arr_goods = new ArrayList<Good>();
    private final int SIZE_OF_ARR = 25;
    private GoodsAdapter goodsAdapter;

    @Override
    public void onClick(View view) {
        Log.d("Show Cart","event: clicked.");
        ArrayList<Good> arrCheckGoods = goodsAdapter.getCheckedGoods();
        Intent intent = new Intent(this, SecondActivity.class);
        if (arrCheckGoods != null) {
            intent.putParcelableArrayListExtra("MyList", arrCheckGoods);
        }
        startActivityForResult(intent, LIST_CODE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("onActivityResult called", "start updating arrayList");
        Log.d("onActivityResult called", "resultCode = "+requestCode+" resultCode="+resultCode);
        if (requestCode == LIST_CODE_REQUEST) {
            if (resultCode == RESULT_OK) {
                super.onActivityResult(requestCode, resultCode, data);

                ArrayList<Good> newCheckedList = data.getParcelableArrayListExtra("MyList");
//                this.arr_goods.stream().forEach(
//                        el -> newCheckedList.stream().filter(
//                                el2 -> el2.getId() != el.getId()).forEach(el3 -> el.setCheck(false))
//                );

                this.arr_goods.stream().forEach(el -> el.setCheck(false));
                newCheckedList.stream().forEach(el ->this.arr_goods.stream().filter(el1-> el1.getId() == el.getId()).findFirst().get().setCheck(true));

                newCheckedList.stream().forEach(el -> Log.d("newChrckedList", el.getName() +" "+ el.getCheck()));
                this.arr_goods.stream().forEach(el -> Log.d("updatedArr_goods", el.getName()+" "+ el.getCheck()));

                this.goodsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {


        this.checkedGoodsCountTextView = (TextView) findViewById(R.id.checkedGoodsCountTextView);
        this.btnShow = (Button) findViewById(R.id.showCheckedBtn);
        this.mailnRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        this.mailnRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.fillData();

        this.goodsAdapter = new GoodsAdapter(this, this.arr_goods, this.checkedGoodsCountTextView);
        this.mailnRecyclerView.setAdapter(goodsAdapter);

        this.btnShow.setOnClickListener(this);

    }

    private void fillData() {
        int i = 0;
        while (i < SIZE_OF_ARR) {
            i++;
            arr_goods.add(new Good(i, " " + "My good №" + Integer.toString(i), Integer.toString(i*i), false));
        }
    }
}