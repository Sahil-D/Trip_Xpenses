package com.example.tripxpenses.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripxpenses.CardModel.CardModel;
import com.example.tripxpenses.Classes.DBHelper;
import com.example.tripxpenses.Classes.TripDetail;
import com.example.tripxpenses.NewTripPage;
import com.example.tripxpenses.R;

import java.util.ArrayList;

public class CardAdaptar extends RecyclerView.Adapter<CardAdaptar.viewHolder> {

    ArrayList<CardModel> list;
    Context context;

    public CardAdaptar(ArrayList<CardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CardAdaptar.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_recyclerview,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdaptar.viewHolder holder, int position) {
        CardModel model=list.get(position);
        holder.textView.setText(model.getTripname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TripDetail.class);
                intent.putExtra("tripname",model.getTripname());
                context.startActivity(intent);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DBHelper helper=new DBHelper(context);

                new AlertDialog.Builder(context)
                        .setTitle("Delete Trip")
                        .setMessage("Are you sure to delete this trip ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if( helper.deleteTrip(model.getTripname())>0)
                                {
                                    list.remove(position);
                                    notifyItemRemoved(position);
                                    Toast.makeText(context,"Deleted sucessfully",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(context,"Problem deleting",Toast.LENGTH_SHORT).show();
                                }

                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();



                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }


}
