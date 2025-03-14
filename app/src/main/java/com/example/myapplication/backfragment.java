package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.FragmentBackBinding;  // ViewBindingのインポート

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class backfragment extends Fragment {

    private ItemAdapter itemAdapter;
    private PlayersItemAdapter playersItemAdapter;
    private TextView victoryTextView;
    private List<cardData> playersItem = new ArrayList<>();
    private SharedViewModel sharedViewModel;
    int totalVictoryPoints = 0;

    public backfragment() {
        // 必須のコンストラクタ
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBackBinding binding = FragmentBackBinding.inflate(inflater, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        victoryTextView = binding.getRoot().findViewById(R.id.victory_count);

        RecyclerView itemListRecyclerView = binding.getRoot().findViewById(R.id.itemListRecyclerView);
        RecyclerView playersItemRecyclerView = binding.getRoot().findViewById(R.id.area);

        itemListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playersItemRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<cardData> itemList = new ArrayList<>();

        // データの登録
        itemList.add(new cardData("パン屋", 2, 0, 0, 1));
        itemList.add(new cardData("酒屋", 1, 1, 0, 1));
        itemList.add(new cardData("ケーキ屋", 1, 0, 1, 1));
        itemList.add(new cardData("オーブン", 0, 3, 0, 1));
        itemList.add(new cardData("倉庫", 0, 0, 0, 1));
        itemList.add(new cardData("協会", 3, 0, 1, 2));
        itemList.add(new cardData("リーンの魔導書", 0, 0, 3, 0));
        itemList.add(new cardData("リッチの魔導書", 0, 0, 3, 0));
        itemList.add(new cardData("フィンガーテストの魔導書", 0, 0, 4, 0));
        itemList.add(new cardData("ミキサーの魔導書", 0, 0, 2, 0));
        itemList.add(new cardData("高級パン屋", 5, 0, 0, 2));
        itemList.add(new cardData("豪華な酒場", 3, 2, 0, 2));
        itemList.add(new cardData("名匠のケーキ屋", 3, 0, 2, 2));
        itemList.add(new cardData("魔法のオーブン", 0, 2, 2, 2));
        itemList.add(new cardData("闇市", 2, 0, 2, 2));
        itemList.add(new cardData("盗賊ギルド", 3, 2, 0, 2));
        itemList.add(new cardData("みかじめ騎士団", 3, 3, 0, 2));
        itemList.add(new cardData("徴税の路地裏", 4, 2, 2, 3));
        itemList.add(new cardData("王立パン協会", 2, 0, 4, 2));
        itemList.add(new cardData("二つ星のブレスレット", 2, 2, 2, 2));
        itemList.add(new cardData("五つ星のネックレス", 2, 2, 2, 2));
        itemList.add(new cardData("武器商人", 2, 2, 0, 1));
        itemList.add(new cardData("大地の収穫祭", 4, 0, 0, 1));
        itemList.add(new cardData("パネテリア遊園地", 0, 0, 0, 1));
        itemList.add(new cardData("黒猫の賭場", 0, 0, 5 ,1));
        itemList.add(new cardData("値切りの指輪", 0, 2, 3, 2));
        itemList.add(new cardData("麗しき女王像", 0, 6, 0, 0));
        itemList.add(new cardData("脱法パン屋", 10, 0, 0, 1));
        itemList.add(new cardData("闇金庫", 5, 0, 0, 0));
        itemList.add(new cardData("パンの妖精　コッペン", 1, 0, 4, 0));
        itemList.add(new cardData("コッペパン神殿", 8, 0, 0, 7));
        itemList.add(new cardData("フランスパンの英雄像", 0, 8, 0, 8));
        itemList.add(new cardData("黒き魔女の館", 0, 0, 8, 7));
        itemList.add(new cardData("ベーグルの塔", 0, 0, 0, 8));
        itemList.add(new cardData("幻虹竜のたまご", 8, 8, 8, 9));
        itemList.add(new cardData("エッグベネディクトモール", 0, 0, 0, 13));
        itemList.add(new cardData("大魔導図書館", 0, 0, 0, 9));
        itemList.add(new cardData("ポレポレ島の秘境", 0, 0, 0, 10));
        itemList.add(new cardData("無限鏡の大迷宮", 0, 0, 0, 10));
        itemList.add(new cardData("黄昏の麦隴", 0, 0, 0, 12));

        sharedViewModel.getPlayersItem().observe(getViewLifecycleOwner(), new Observer<List<cardData>>() {
            @Override
            public void onChanged(List<cardData> playerData) {
                if (playerData != null) {
                    playersItem = new ArrayList<>(playerData);
                    playersItemAdapter.setItemList(playersItem);  // ここでアイテムリストを更新
                } else {
                    playersItem = new ArrayList<>();
                    playersItemAdapter.setItemList(playersItem);
                }
                totalVictoryPoints = sharedViewModel.updateVictoryPoint(playersItem);
                // Activity に勝利ポイントを渡して homefragment を更新
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).updateVictoryPoint(totalVictoryPoints);
                }
                victoryTextView.setText("vp : " + totalVictoryPoints);
            }
        });

        sharedViewModel.getMoneyCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                updateVictoryPoints();
            }
        });


        // itemList 用アダプターを設定
        itemAdapter = new ItemAdapter(itemList, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // アイテムがクリックされたときの処理
                cardData selectedItem = itemList.get(position);
                // playersItem にアイテム追加処理
                playersItem.add(selectedItem);
                playersItemAdapter.notifyDataSetChanged();  // 更新
                sharedViewModel.addItem(selectedItem);
                updateVictoryPoints();
            }
        });

        // playersItem 用のアダプター設定
        playersItemAdapter = new PlayersItemAdapter(playersItem, new PlayersItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // playersItem 内のアイテムクリック処理
                cardData selectedItem = playersItem.get(position);
                boolean bankchek = false;
                playersItem.remove(selectedItem);
                playersItemAdapter.notifyDataSetChanged();  // 更新
                sharedViewModel.deleteItem(selectedItem.getName());
                for (cardData item:playersItem){
                    if(item.getName().equals("闇金庫")){
                        bankchek = true;
                        break;
                    }
                }
                if (!bankchek)sharedViewModel.setMoneyCount(0);
                updateVictoryPoints();
            }
        });

        // RecyclerView にアダプター設定
        itemListRecyclerView.setAdapter(itemAdapter);
        playersItemRecyclerView.setAdapter(playersItemAdapter);

        // playersItem の更新を監視
        sharedViewModel.getPlayersItem().observe(getViewLifecycleOwner(), playerData -> {
            playersItem.clear();
            if (playerData != null) {
                playersItem.addAll(playerData);
            }
            playersItemAdapter.notifyDataSetChanged();
            updateVictoryPoints();
        });

        return binding.getRoot();
    }

    // 勝利ポイントを更新
    private void updateVictoryPoints() {
        int totalVictoryPoints = sharedViewModel.updateVictoryPoint(playersItem);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateVictoryPoint(totalVictoryPoints);
        }
        victoryTextView.setText("vp : " + totalVictoryPoints);
    }

    // アイテムリストを取得するメソッド
    private List<cardData> getItemList() {
        List<cardData> itemList = new ArrayList<>();
        itemList.add(new cardData("パン屋", 2, 0, 0, 1));
        itemList.add(new cardData("酒屋", 1, 1, 0, 1));
        // 他のアイテムを追加
        return itemList;
    }
}