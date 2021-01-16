package com.example.tripxpenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tripxpenses.Adapters.CardAdaptar;
import com.example.tripxpenses.CardModel.CardModel;
import com.example.tripxpenses.Classes.DBHelper;
import com.example.tripxpenses.Classes.RecyclerItemClickListener;
import com.example.tripxpenses.Classes.newf.PersonFragment;
import com.example.tripxpenses.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#CC0EEC'>Trip eXpenses</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));

        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Button addbtn=findViewById(R.id.addbtn);


        DBHelper helper=new DBHelper(this);
        ArrayList<CardModel> list=helper.getTrips();

        CardAdaptar adapter=new CardAdaptar(list,this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager ollm=new LinearLayoutManager((this));
        binding.recyclerView.setLayoutManager(ollm);





      addbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent i=new Intent(MainActivity.this,NewTripPage.class);
              startActivity(i);

          }
      });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        menu.findItem(R.id.share);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share:
                Intent send=new Intent();
                send.setAction(Intent.ACTION_SEND);
                send.setType("text/plain");
                send.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/drive/folders/1Ob-Q0ZZTkCRZEHtkAWAX6BQ-X7xuZE0z?usp=sharing");
                startActivity(Intent.createChooser(send,null));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed()
    {

        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure to exit the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNeutralButton("About us", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("About Us")
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setMessage("Sahil Dawara                sunnydawara00@gmail.com").show();
                    }
                }).show();
    }



}
