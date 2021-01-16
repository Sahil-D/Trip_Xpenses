package com.example.tripxpenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripxpenses.Classes.DBHelper;
import com.example.tripxpenses.Classes.newf.NameFragment;
import com.example.tripxpenses.Classes.newf.PersonFragment;
import com.example.tripxpenses.databinding.ActivityMainBinding;
import com.example.tripxpenses.databinding.ActivityNewTripPageBinding;

// This Activity will have two fragments -> NameFragment , PersonFragment
public class NewTripPage extends AppCompatActivity {

    ActivityNewTripPageBinding binding;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#CC0EEC'>Add Trip</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        super.onCreate(savedInstanceState);
        binding= ActivityNewTripPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        DBHelper helper=new DBHelper(this);

        NameFragment fragment=new NameFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FLinearLayout,fragment);
        transaction.commit();


    }


}