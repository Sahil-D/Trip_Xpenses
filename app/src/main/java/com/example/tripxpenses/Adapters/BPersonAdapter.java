package com.example.tripxpenses.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripxpenses.CardModel.BPersonModel;
import com.example.tripxpenses.R;

import java.util.ArrayList;

public class BPersonAdapter extends RecyclerView.Adapter<BPersonAdapter.viewHolder> {

    ArrayList<BPersonModel> list;
    Context context;

    public BPersonAdapter(ArrayList<BPersonModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_personbalance,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BPersonModel model=list.get(position);
        holder.pnamebal.setText(model.getPnamebal());
        String bals=model.getPbalance();
        Double bal=Double.parseDouble(model.getPbalance());
        if(bal>0)
        { bals="+"+bals; }
        holder.pbalance.setText(bals);

        if(bal<0)
        {
            holder.pbalance.setTextColor(ContextCompat.getColor(context,R.color.red));
        }
        else
        {
            holder.pbalance.setTextColor(ContextCompat.getColor(context,R.color.green));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView pnamebal;
        TextView pbalance;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pnamebal=itemView.findViewById(R.id.pnamebal);
            pbalance=itemView.findViewById(R.id.pbalance);

        }
    }
}
