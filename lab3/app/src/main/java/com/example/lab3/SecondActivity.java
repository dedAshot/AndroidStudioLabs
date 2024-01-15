package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lab3.adapters.GoodsAdapter;
import com.example.lab3.models.Good;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    static final int LIST_CODE_REQUEST=45;
    private TextView checkedGoodsCountTextView;
    private int checkedGoodsCount=0;
    private Button btnReturn;
    private RecyclerView secondRecyclerView;
    private ArrayList<Good> arrCheckedGoods = new ArrayList<Good>();
    private GoodsAdapter checkedGoodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.arrCheckedGoods = getIntent().getParcelableArrayListExtra("MyList");

        initView();
    }

    private void initView(){
        this.checkedGoodsCountTextView = (TextView) findViewById(R.id.checkedGoodsCountTextViewSecAct);
        this.btnReturn = (Button) findViewById(R.id.btnToMainActivity);
        this.secondRecyclerView= (RecyclerView) findViewById(R.id.secondRecyclerView);
        this.secondRecyclerView.setLayoutManager(new LinearLayoutManager( this));

        this.checkedGoodsAdapter = new GoodsAdapter(this, this.arrCheckedGoods, this.checkedGoodsCountTextView){
            private int checkedGoodsCount = arrCheckedGoods.size();
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isShown()) {
                    int i = (int) compoundButton.getTag(), k=0;
                    Log.d("onCheckedChanged", "del: "+k + " i="+i);


                    arrCheckedGoods.remove( i);
                    notifyDataSetChanged();

                    if (isChecked) {
                        ++this.checkedGoodsCount;
                    } else {
                        --this.checkedGoodsCount;

                    };
                    checkedGoodsCountTextView.setText("cart goods count: "+this.checkedGoodsCount);
                }
            }
        };

        this.btnReturn.setOnClickListener(this);
        this.secondRecyclerView.setAdapter(checkedGoodsAdapter);


    }

    @Override
    public void onClick(View view) {

        Log.d("Show Cart","event: clicked.");
        this.onStop();
        Intent intent = new Intent(this, MainActivity.class);

        intent.putParcelableArrayListExtra("MyList", this.arrCheckedGoods);

        setResult(RESULT_OK, intent);
        onBackPressed();
    }


}