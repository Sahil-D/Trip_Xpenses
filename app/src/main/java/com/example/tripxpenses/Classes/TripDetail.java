

package com.example.tripxpenses.Classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.BaseBundle;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tripxpenses.Adapters.BPersonAdapter;
import com.example.tripxpenses.CardModel.BPersonModel;
import com.example.tripxpenses.MainActivity;
import com.example.tripxpenses.R;
import com.example.tripxpenses.databinding.ActivityMainBinding;
import com.example.tripxpenses.databinding.ActivityTripDetailBinding;

import java.util.ArrayList;

//This activity will show balances of each person of a particular trip

public class TripDetail extends AppCompatActivity {

    ActivityTripDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#CC0EEC'>Trip Details</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        super.onCreate(savedInstanceState);
        binding= ActivityTripDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper=new DBHelper(this);
        binding.tname.setText(getIntent().getStringExtra("tripname"));

        ArrayList<BPersonModel> list=helper.getPersons(getIntent().getStringExtra("tripname"));

        BPersonAdapter adapter=new BPersonAdapter(list,this);
        binding.detailRecyclerView.setAdapter(adapter);

        LinearLayoutManager ollm=new LinearLayoutManager((this));
        binding.detailRecyclerView.setLayoutManager(ollm);

        String trname=getIntent().getStringExtra("tripname");


        ArrayList<String> names=helper.getpnames(trname);
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,names);
        binding.contributor.setAdapter(adapter1);


        Cursor cursor =helper.getnop(trname);
        int nop=cursor.getInt(0);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.expense.getText().toString().length()==0)
                {
                    Toast.makeText(TripDetail.this,"Please Enter Expense",Toast.LENGTH_SHORT).show();
                }
                else {
                    double expense = Double.parseDouble(binding.expense.getText().toString());
                    String contributorName = binding.contributor.getSelectedItem().toString();

                    double other = expense / nop;

                    helper.updatebalance(getIntent().getStringExtra("tripname"), contributorName, expense, other);
                    Toast.makeText(TripDetail.this, "Updated", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(TripDetail.this, TotalExpenses.class);
                    i.putExtra("tname", trname);
                    startActivity(i);

                }

            }
        });

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {    //Just for fun
            @Override
            public void onRefresh() {

                binding.swipe.setRefreshing(false);
            }
        });

        binding.seebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TripDetail.this,TotalExpenses.class);
                i.putExtra("tname",trname);
                startActivity(i);
            }
        });



    }

    @Override
    public void onBackPressed() {
        String tname=getIntent().getStringExtra("tname");
        Intent i=new Intent(TripDetail.this, MainActivity.class);
        i.putExtra("tripname",tname);
        startActivity(i);

    }
}