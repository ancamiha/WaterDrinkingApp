package com.example.waterdrinkingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waterdrinkingapp.Adapter;
import com.example.waterdrinkingapp.R;
import com.example.waterdrinkingapp.db.AppDatabase;
import com.github.lzyzsd.circleprogress.CircleProgress;

import java.util.ArrayList;


public class MainFragment extends Fragment implements View.OnClickListener {
    private final String TAG = MainFragment.class.getSimpleName();
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;
    CircleProgress circleProgress;

    volatile int percent;
    volatile Integer waterIntake;
    volatile Integer currentQuantity;
    String registerId;

    public Integer getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Integer waterIntake) {
        this.waterIntake = waterIntake;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    private MainFragmentViewModel mainModel;

    Button button250, button500, button750;
    TextView reachTarget0, reachTarget1, target0, target1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String receivedUid = getArguments().getString("userUID");
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        button250 = view.findViewById(R.id.button250);
        button500 = view.findViewById(R.id.button500);
        button750 = view.findViewById(R.id.button750);
        circleProgress = view.findViewById(R.id.circle_progress);
        reachTarget0 = view.findViewById(R.id.reachTarget0);
        reachTarget1 = view.findViewById(R.id.reachTarget1);
        target0 = view.findViewById(R.id.target0);
        target1 = view.findViewById(R.id.target1);


        AppDatabase database = AppDatabase.getDatabase(getContext());
        database.informationDao().getWaterIntake(receivedUid).observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                target1.setText(integer + "ml");
            }
        });

        database.informationDao().getCurrentQuantity(receivedUid).observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                reachTarget1.setText(integer + "ml");
            }
        });

//        new Thread() {
//            @Override
//            public void run() {
//                database.informationDao().updatePercent(receivedUid);
//            }
//        }.start();
//
//        database.informationDao().getPercent(receivedUid).observe(getViewLifecycleOwner(), new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                circleProgress.setProgress(integer);
//            }
//        });

        button250.setOnClickListener(this::onClick);
        button500.setOnClickListener(this::onClick);
        button750.setOnClickListener(this::onClick);

//        int percent = (int) ((int) getCurrentQuantity() * 100 / (int) getWaterIntake());
//        new Thread() {
//            @Override
//            public void run() {
//                waterIntake = AppDatabase.getDatabase(getContext()).informationDao().getWaterIntake(receivedUid);
//                currentQuantity = AppDatabase.getDatabase(getContext()).informationDao().getCurrentQuantity(receivedUid);
//                setWaterIntake(waterIntake);
//                setCurrentQuantity(currentQuantity);
////                int percent = (int) ((int) currentQuantity * 100 / (int) waterIntake);
////                setPercent(percent);
//            }
//        }.start();

//
//        mainModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
//        final Observer<Integer> quantityObserver = new Observer<Integer>() {
//            @Override
//            public void onChanged(final Integer newName) {
//                reachTarget1.setText(currentQuantity + "ml");
//            }
//        };
//        mainModel.getToReachTarget().observe(getViewLifecycleOwner(), quantityObserver);
//
//        final Observer<Integer> percentObserver = new Observer<Integer>() {
//            @Override
//            public void onChanged(final Integer newName) {
//                circleProgress.setProgress(percent);
//            }
//        };
//        mainModel.getMyPercent().observe(getViewLifecycleOwner(), percentObserver);
//
//        reachTarget1.setText(this.getCurrentQuantity() + "ml");
//        target1.setText(this.getWaterIntake() + "ml");
//        circleProgress.setProgress(this.getPercent());

//        items = new ArrayList<>();
//        items.add("Card 1");
//        items.add("Card 2");

//        recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new Adapter(getContext(), items);

//        recyclerView.setAdapter(adapter);

        return view;
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
                        AppDatabase.getDatabase(v.getContext()).informationDao().updateCurrentQuantity(getCurrentQuantity(), getRegisterId());
                    }
                }.start();
                percent = getCurrentQuantity() * 100 / getWaterIntake();
                this.setPercent(percent);
                break;
            case R.id.button500:
                setCurrentQuantity(getCurrentQuantity() + 500);
                new Thread() {
                    @Override
                    public void run() {
                        AppDatabase.getDatabase(v.getContext()).informationDao().updateCurrentQuantity(getCurrentQuantity(), getRegisterId());
                    }
                }.start();
                percent = getCurrentQuantity() * 100 / getWaterIntake();
                this.setPercent(percent);
                break;
            case R.id.button750:
                setCurrentQuantity(getCurrentQuantity() + 700);
                new Thread() {
                    @Override
                    public void run() {
                        AppDatabase.getDatabase(v.getContext()).informationDao().updateCurrentQuantity(getCurrentQuantity(), getRegisterId());
                    }
                }.start();
                percent = getCurrentQuantity() * 100 / getWaterIntake();
                this.setPercent(percent);
                break;
        }
    }
}