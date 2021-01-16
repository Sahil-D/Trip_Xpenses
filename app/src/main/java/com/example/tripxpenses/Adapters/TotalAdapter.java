package com.example.tripxpenses.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripxpenses.CardModel.TotalModel;
import com.example.tripxpenses.R;

import java.util.ArrayList;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.viewHolder> {

    ArrayList<TotalModel> list;
    Context context;

    public TotalAdapter(ArrayList<TotalModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_totalexp,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TotalModel model=list.get(position);
        holder.totalexp.setText(model.getTotalexp());
        holder.totalpname.setText(model.getTotalpname());
        holder.sno.setText(model.getSno());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView totalexp;
        TextView totalpname;
        TextView sno;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            sno=itemView.findViewById(R.id.sno);
            totalexp=itemView.findViewById(R.id.totalexp);
            totalpname=itemView.findViewById(R.id.totalpname);
        }
    }
}
