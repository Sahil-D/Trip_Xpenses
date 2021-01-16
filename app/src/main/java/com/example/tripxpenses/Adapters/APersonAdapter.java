package com.example.tripxpenses.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripxpenses.CardModel.APersonModel;
import com.example.tripxpenses.R;

import java.util.ArrayList;

public class APersonAdapter extends  RecyclerView.Adapter<APersonAdapter.viewHolder> {

    ArrayList<APersonModel> list;
    Context context;

    public APersonAdapter(ArrayList<APersonModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_person,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        APersonModel model=list.get(position);
        holder.pnum.setText(model.getPnum());
        holder.pname.setText(model.getPname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {   TextView pnum;
        EditText pname;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pname=itemView.findViewById(R.id.samplepname);
            pnum=itemView.findViewById(R.id.samplepnum);

            pname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(getAdapterPosition()).setPname(pname.getText().toString());       // to get latest edit text value
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }


}
