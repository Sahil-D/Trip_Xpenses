package com.example.tripxpenses.Classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tripxpenses.Adapters.TotalAdapter;
import com.example.tripxpenses.CardModel.TotalModel;
import com.example.tripxpenses.R;

import java.util.ArrayList;

// This activity deals with showing every expense of a particular trip
public class TotalExpenses extends AppCompatActivity {

        Button back;
        RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#CC0EEC'>Trip Details</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_expenses);
        DBHelper helper =new DBHelper(this);


        recyclerView=findViewById(R.id.totalexpRecycler);

        ArrayList<TotalModel> list=helper.getTotal(getIntent().getStringExtra("tname"));


        TotalAdapter adapter=new TotalAdapter(list,TotalExpenses.this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        back=findViewById(R.id.button3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tname=getIntent().getStringExtra("tname");
                Intent i=new Intent(TotalExpenses.this,TripDetail.class);
                i.putExtra("tripname",tname);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {
        String tname=getIntent().getStringExtra("tname");
        Intent i=new Intent(TotalExpenses.this,TripDetail.class);
        i.putExtra("tripname",tname);
        startActivity(i);

    }
}