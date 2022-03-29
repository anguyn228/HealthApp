package com.example.healthapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.DataModel;
import com.example.healthapp.DoctorDetails;
import com.example.healthapp.R;

import java.util.ArrayList;

public class DoctosListAdapter extends RecyclerView.Adapter<DoctosListAdapter.UI> {
    private Context context;
    private ArrayList<DataModel> doctorList;

    public DoctosListAdapter(Context context, ArrayList<DataModel> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.doctor_list_ui, null);

        return new UI(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        DataModel model = doctorList.get(position);

        holder.name.setText(model.getName());
        holder.card.setOnClickListener(v -> {
            context.startActivity(new Intent(context, DoctorDetails.class)
                    .putExtra("userid", model.getUserId())
                    .putExtra("name", model.getName()));
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class UI extends RecyclerView.ViewHolder {
        CardView card;
        TextView name;

        public UI(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            name = itemView.findViewById(R.id.name);
        }
    }
}
