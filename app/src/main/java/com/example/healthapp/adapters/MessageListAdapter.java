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
import com.example.healthapp.DoctorActivity;
import com.example.healthapp.R;
import com.example.healthapp.models.MessageModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import static com.example.healthapp.DoctorActivity.ID;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.UI> {
    private Context context;
    private ArrayList<MessageModel> messageList;
    private DataBaseHelper dataBaseHelper;

    public MessageListAdapter(Context context, ArrayList<MessageModel> messageList) {
        this.context = context;
        this.messageList = messageList;
        dataBaseHelper = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.message_ui, null);

        return new MessageListAdapter.UI(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        MessageModel model = messageList.get(position);

        holder.message.setText(model.getMessage());
        holder.sender.setText(model.getSender());

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
                MessageModel messageModel = new MessageModel(model.getUserID(), ID, message);
                if (dataBaseHelper.addMessage(messageModel)) {
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
        return messageList.size();
    }

    public class UI extends RecyclerView.ViewHolder {
        CardView card;
        TextView message;
        TextView sender;

        public UI(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            message = itemView.findViewById(R.id.message);
            sender = itemView.findViewById(R.id.sender);
        }
    }
}
