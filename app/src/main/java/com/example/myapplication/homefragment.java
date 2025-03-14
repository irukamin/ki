package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class homefragment extends Fragment {

    int money_number = 0;
    int power_number = 0;
    int magic_number = 0;
    int victory_point = 0;

    int totalVictoryPoints;
    private SharedViewModel sharedViewModel;

    private TextView victoryTextView;

    public homefragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);

        // TextViewを取得
        TextView moneyTextView = view.findViewById(R.id.money_text);
        TextView powerTextView = view.findViewById(R.id.power_text);
        TextView magicTextView = view.findViewById(R.id.magic_text);
        victoryTextView = view.findViewById(R.id.victory_point);
        moneyTextView.setText(String.valueOf(money_number));  // 初期値0を表示
        powerTextView.setText(String.valueOf(power_number));  // 初期値0を表示
        magicTextView.setText(String.valueOf(magic_number));  // 初期値0を表示

        // ボタンを取得
        Button incrementMoneyButton = view.findViewById(R.id.money_up);
        Button decrementMoneyButton = view.findViewById(R.id.money_down);
        Button incrementPowerButton = view.findViewById(R.id.power_up);
        Button decrementPowerButton = view.findViewById(R.id.power_down);
        Button incrementMagicButton = view.findViewById(R.id.magic_up);
        Button decrementMagicButton = view.findViewById(R.id.magic_down);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getMoneyNumber().observe(getViewLifecycleOwner(), money -> {
            moneyTextView.setText(String.valueOf(money));
        });

        sharedViewModel.getPowerNumber().observe(getViewLifecycleOwner(), power -> {
            powerTextView.setText(String.valueOf(power));
        });

        sharedViewModel.getMagicNumber().observe(getViewLifecycleOwner(), magic -> {
            magicTextView.setText(String.valueOf(magic));
        });

        // Victory Pointsの更新
        sharedViewModel.getVictoryPoints().observe(getViewLifecycleOwner(), victoryPoints -> {
            updateVictoryPoint(victoryPoints);
        });

        // ボタンのクリックリスナー
        view.findViewById(R.id.money_up).setOnClickListener(v -> {
            int currentMoney = sharedViewModel.getMoneyNumber().getValue();
            sharedViewModel.setMoneyNumber(currentMoney + 1);
        });

        view.findViewById(R.id.money_down).setOnClickListener(v -> {
            int currentMoney = sharedViewModel.getMoneyNumber().getValue();
            if (currentMoney > 0) {
                sharedViewModel.setMoneyNumber(currentMoney - 1);
            }
        });

        view.findViewById(R.id.power_up).setOnClickListener(v -> {
            int currentPower = sharedViewModel.getPowerNumber().getValue();
            sharedViewModel.setPowerNumber(currentPower + 1);
        });

        view.findViewById(R.id.power_down).setOnClickListener(v -> {
            int currentPower = sharedViewModel.getPowerNumber().getValue();
            if (currentPower > 0) {
                sharedViewModel.setPowerNumber(currentPower - 1);
            }
        });

        view.findViewById(R.id.magic_up).setOnClickListener(v -> {
            int currentMagic = sharedViewModel.getMagicNumber().getValue();
            sharedViewModel.setMagicNumber(currentMagic + 1);
        });

        view.findViewById(R.id.magic_down).setOnClickListener(v -> {
            int currentMagic = sharedViewModel.getMagicNumber().getValue();
            if (currentMagic > 0) {
                sharedViewModel.setMagicNumber(currentMagic - 1);
            }
        });
        return view;
    }

    public void updateVictoryPoint(int victoryPoint) {
        if (victoryTextView != null) {
            victoryTextView.setText("vp : " + victoryPoint);
        }
    }
}
