package com.example.waterdrinkingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;

    public Adapter(Context context, List<String> data) {
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

        switch (position) {
            case 0:
                holder.reachTarget0.setVisibility(View.VISIBLE);
                holder.reachTarget1.setVisibility(View.VISIBLE);
                holder.target0.setVisibility(View.VISIBLE);
                holder.target1.setVisibility(View.VISIBLE);
                holder.button250.setVisibility(View.INVISIBLE);
                holder.button500.setVisibility(View.INVISIBLE);
                holder.button750.setVisibility(View.INVISIBLE);
                break;
            case 1:
                holder.reachTarget0.setVisibility(View.INVISIBLE);
                holder.reachTarget1.setVisibility(View.INVISIBLE);
                holder.target0.setVisibility(View.INVISIBLE);
                holder.target1.setVisibility(View.INVISIBLE);
                holder.button250.setVisibility(View.VISIBLE);
                holder.button500.setVisibility(View.VISIBLE);
                holder.button750.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button button250, button500, button750;
        TextView reachTarget0, target0, reachTarget1, target1;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            button250 = itemView.findViewById(R.id.button250);
            button500 = itemView.findViewById(R.id.button500);
            button750 = itemView.findViewById(R.id.button750);

            reachTarget0 = itemView.findViewById(R.id.reachTarget0);
            reachTarget1 = itemView.findViewById(R.id.reachTarget1);
            target0 = itemView.findViewById(R.id.target0);
            target1 = itemView.findViewById(R.id.target1);

            reachTarget1.setText("200ml");
            target1.setText("2200ml");
        }
    }
}
