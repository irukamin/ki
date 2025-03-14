package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<cardData> itemList;
    private OnItemClickListener onItemClickListener;

    public ItemAdapter(List<cardData> itemList, OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setItemList(List<cardData> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false); // アイテムリストのレイアウト
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cardData card = itemList.get(position);
        holder.textView.setText(card.getName());

//        // "闇金庫" のみボタンを表示
//        if (card.getName().equals("闇金庫")) {
//            holder.btnAdd.setVisibility(View.VISIBLE);
//            holder.btnSubtract.setVisibility(View.VISIBLE);
//        } else {
//            holder.btnAdd.setVisibility(View.GONE);
//            holder.btnSubtract.setVisibility(View.GONE);
//        }

        // クリックリスナー
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button btnAdd, btnSubtract;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemName);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnSubtract = itemView.findViewById(R.id.btnSubtract);
        }
    }
}

/*
計算は終了
↓できれば一覧
±ボタンが全てのカードの横についているー＞どちらでもよい
カードがまとまればいいな
ベネ系の時についでに消せたらいいな？後条件できてたら
 */