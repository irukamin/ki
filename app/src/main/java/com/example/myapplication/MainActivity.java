package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 最初に表示するフラグメントとしてhomefragmentを設定
        if (savedInstanceState == null) {
            showFragment(new homefragment());
        }

        // ボタンの設定
        Button homeButton = findViewById(R.id.homeButton);
        Button backButton = findViewById(R.id.backButton);

        // HomeFragmentに切り替えるボタンのクリックリスナー
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new homefragment());  // homefragmentを表示
            }
        });

        // BackFragmentに切り替えるボタンのクリックリスナー
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new backfragment());  // backfragmentを表示
            }
        });
    }

    // フラグメントを切り替えるためのヘルパーメソッド
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);  // フラグメントを置き換え
        transaction.addToBackStack(null);  // バックスタックに追加することで戻る操作ができる
        transaction.commit();
    }
    public void updateVictoryPoint(int victoryPoint) {
        // homefragment を取得して victoryTextView を更新
        homefragment homeFragment = (homefragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
        if (homeFragment != null) {
            homeFragment.updateVictoryPoint(victoryPoint);
        }
    }
}
