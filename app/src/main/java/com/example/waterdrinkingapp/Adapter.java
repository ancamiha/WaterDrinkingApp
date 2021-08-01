package com.example.waterdrinkingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waterdrinkingapp.db.AppDatabase;
import com.example.waterdrinkingapp.fragments.MainFragment;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final String TAG = MainActivity.class.getSimpleName();

    private LayoutInflater layoutInflater;
    private List<String> data;

    int waterIntake;
    int currentQuantity;
    String registerId;
    public int getWaterIntake() {
        return waterIntake;
    }
    public void setWaterIntake(int waterIntake) {
        this.waterIntake = waterIntake;
    }
    public int getCurrentQuantity() {
        return currentQuantity;
    }
    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
    public String getRegisterId() {
        return registerId;
    }
    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button button250, button500, button750;
        TextView reachTarget0, target0, reachTarget1, target1;
        MainFragment fragment = new MainFragment();

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d(TAG, "water intake = " + getWaterIntake());

            button250 = itemView.findViewById(R.id.button250);
            button500 = itemView.findViewById(R.id.button500);
            button750 = itemView.findViewById(R.id.button750);

            button250.setOnClickListener(this);

            reachTarget0 = itemView.findViewById(R.id.reachTarget0);
            reachTarget1 = itemView.findViewById(R.id.reachTarget1);
            target0 = itemView.findViewById(R.id.target0);
            target1 = itemView.findViewById(R.id.target1);

            int percent = getCurrentQuantity() * 100 / getWaterIntake();
            fragment.setPercent(percent);

            reachTarget1.setText(getCurrentQuantity() + "ml");
            target1.setText(getWaterIntake() + "ml");
        }

        @Override
        public void onClick(View v) {
            int percent;
            switch (v.getId()) {
                case R.id.button250:
                    setCurrentQuantity(getCurrentQuantity() + 250);
                    new Thread() {
                        @Override
                        public void run() {
                            AppDatabase.getDatabase(v.getContext()).informationDao().updateDetails(getCurrentQuantity(), getRegisterId());
                        }
                    }.start();
                    percent = getCurrentQuantity() * 100 / getWaterIntake();
                    fragment.setPercent(percent);
                    break;
                case R.id.button500:
                    setCurrentQuantity(getCurrentQuantity() + 500);
                    new Thread() {
                        @Override
                        public void run() {
                            AppDatabase.getDatabase(v.getContext()).informationDao().updateDetails(getCurrentQuantity(), getRegisterId());
                        }
                    }.start();
                    percent = getCurrentQuantity() * 100 / getWaterIntake();
                    fragment.setPercent(percent);
                    break;
                case R.id.button750:
                    setCurrentQuantity(getCurrentQuantity() + 700);
                    new Thread() {
                        @Override
                        public void run() {
                            AppDatabase.getDatabase(v.getContext()).informationDao().updateDetails(getCurrentQuantity(), getRegisterId());
                        }
                    }.start();
                    percent = getCurrentQuantity() * 100 / getWaterIntake();
                    fragment.setPercent(percent);
                    break;
            }
        }
    }
}
