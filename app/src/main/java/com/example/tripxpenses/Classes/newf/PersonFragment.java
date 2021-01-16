package com.example.tripxpenses.Classes.newf;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripxpenses.Adapters.APersonAdapter;
import com.example.tripxpenses.CardModel.APersonModel;
import com.example.tripxpenses.Classes.DBHelper;
import com.example.tripxpenses.MainActivity;
import com.example.tripxpenses.NewTripPage;
import com.example.tripxpenses.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class PersonFragment extends Fragment {
    TextView tvp;
    TextView tvtn;
    Button btnadd,backbutton;
    RecyclerView addprecyclerView;


    public PersonFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_person, container, false);

        DBHelper helper=new DBHelper(getContext());
        tvp=view.findViewById(R.id.tvperson);


        tvtn=view.findViewById(R.id.tvtripname);
        btnadd=view.findViewById(R.id.addbtnfrag);
        backbutton=view.findViewById(R.id.backbutton);
        addprecyclerView=view.findViewById(R.id.addprecyclerView);


        Bundle bundle=this.getArguments();
        tvp.setText(bundle.getString("nop"));
        tvtn.setText(bundle.getString("tvtripname"));
        int nop=Integer.parseInt(bundle.getString("nop"));


        ArrayList<APersonModel> list=new ArrayList<>();

        for(int i=1;i<=nop;i++)
        {
//            list.add(new APersonModel(""+i,"P "+i));
            list.add(new APersonModel(""+i,"P"+i));

        }

        APersonAdapter adapter=new APersonAdapter(list,getContext());
        addprecyclerView.setAdapter(adapter);


        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        addprecyclerView.setLayoutManager(llm);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < nop; i++) {
                    arrayList.add(list.get(i).getPname());
                }

                Set<String> sets = new HashSet<String>(arrayList);
                if (sets.size() == arrayList.size())
                {

                    boolean isinsert = helper.insertTrip(tvtn.getText().toString(), nop, arrayList);
                    if (isinsert) {
                        Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Problem adding", Toast.LENGTH_SHORT).show();
                    }

                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getContext(),"Please add different names",Toast.LENGTH_SHORT).show();
                }
            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                Bundle bundle1=new Bundle();
//                bundle1.putString("type","2");
              NameFragment fragment=new NameFragment();
//                fragment.setArguments(bundle1);
            getFragmentManager().beginTransaction().replace(R.id.FLinearLayout,fragment).commit();
            }
        });

        return view;
    }
}