package com.example.healthapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.DataBaseHelper;
import com.example.healthapp.R;
import com.example.healthapp.models.BillingModel;
import com.example.healthapp.models.MessageModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import static com.example.healthapp.CashierActivity.USER_ID;

public class BillingAdapter extends RecyclerView.Adapter<BillingAdapter.UI> {
    private Context context;
    private ArrayList<BillingModel> billingList;
    private DataBaseHelper dataBaseHelper;

    public BillingAdapter(Context context, ArrayList<BillingModel> billingList) {
        this.context = context;
        this.billingList = billingList;
        dataBaseHelper = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.billing_ui, null);

        return new UI(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        BillingModel model = billingList.get(position);
        holder.userid.setText(model.getUserID().toUpperCase());
        holder.amount.setText(model.getAmount());
        holder.status.setText(model.getStatus());
        holder.card.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            AlertDialog dialog = builder.create();

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.custom_input, null);
            TextInputEditText addressTF = view.findViewById(R.id.addressTF);
            Button searchBtn = view.findViewById(R.id.searchBtn);
            searchBtn.setOnClickListener(vw -> {
                String message = addressTF.getText().toString().trim();
                if (message.isEmpty()) {
                    Toast.makeText(context, "Message is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                MessageModel messageModel = new MessageModel(model.getUserID(), USER_ID, message);
                if(dataBaseHelper.addMessage(messageModel)){
                    Toast.makeText(context, "Message was sent successfully", Toast.LENGTH_SHORT).show();
                }
                // send message
                dialog.dismiss();
            });

            dialog.setTitle("Enter Message");
            dialog.setView(view);
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return billingList.size();
    }

    public class UI extends RecyclerView.ViewHolder {
        CardView card;
        TextView userid;
        TextView amount;
        TextView status;

        public UI(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            userid = itemView.findViewById(R.id.userid);
            amount = itemView.findViewById(R.id.amount);
            status = itemView.findViewById(R.id.status);
        }
    }
}
