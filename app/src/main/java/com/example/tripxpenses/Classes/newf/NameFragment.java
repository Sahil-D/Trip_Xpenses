package com.example.tripxpenses.Classes.newf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripxpenses.CardModel.CardModel;
import com.example.tripxpenses.Classes.DBHelper;
import com.example.tripxpenses.R;

import java.util.ArrayList;


public class NameFragment extends Fragment {
    Button button;
    EditText edtperson;
    EditText edttripname;


    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_name, container, false);

        button=view.findViewById(R.id.button2);
        edtperson=view.findViewById(R.id.tpersons);
        edttripname=view.findViewById(R.id.tnamefragment);

        //
        DBHelper helper=new DBHelper(getContext());
        ArrayList<String> tnames=helper.gettnames();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nop=edtperson.getText().toString();
                String tname=edttripname.getText().toString();
                if(nop.length()==0 || tname.length()==0)
                {
                    Toast.makeText(getContext(),"Please fill both fields",Toast.LENGTH_SHORT).show();
                }
                else
                if(tnames.contains(tname))                  // to check duplicacy of trips
                {
                    Toast.makeText(getContext(),"Trip already exists",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(Integer.parseInt(nop)<2)
                    { Toast.makeText(getContext(),"Please add atleast 2 members",Toast.LENGTH_SHORT).show();}
                    else {
                        Bundle bundle = new Bundle();
                        bundle.putString("nop", nop);
                        bundle.putString("tvtripname", edttripname.getText().toString());

                        PersonFragment fragment = new PersonFragment();
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.FLinearLayout, fragment).commit();
                    }

            }
        }});




        return view;
    }
}