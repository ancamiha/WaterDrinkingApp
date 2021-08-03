package com.example.waterdrinkingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waterdrinkingapp.db.HistoryEntity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final String TAG = Adapter.class.getSimpleName();

    private LayoutInflater layoutInflater;
    private List<HistoryEntity> data;

    public Adapter(Context context, List<HistoryEntity> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the text view with data received

        HistoryEntity entity = data.get(position);
        holder.date.setText(entity.getDate());
        holder.quantity.setText(String.format("%s", entity.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, quantity;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
