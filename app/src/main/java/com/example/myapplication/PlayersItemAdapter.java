package com.example.myapplication;

import android.content.Context;
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

public class PlayersItemAdapter extends RecyclerView.Adapter<PlayersItemAdapter.ViewHolder> {

    private List<cardData> itemList;
    private OnItemClickListener onItemClickListener;

    public PlayersItemAdapter(List<cardData> itemList, OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setItemList(List<cardData> List){
        itemList = List;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false); // 作成したレイアウトを使用
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cardData cardData = itemList.get(position);
        holder.textView.setText(cardData.getName());

        Context context = holder.itemView.getContext();
        SharedViewModel sharedViewModel = new ViewModelProvider((FragmentActivity) context).get(SharedViewModel.class);

        System.out.println(cardData.getName());
        // 領地エリアで "闇金庫" のみボタンを表示
        if (cardData.getName().equals("闇金庫")) {
            // "闇金庫" の場合にのみボタンを表示
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.btnSubtract.setVisibility(View.VISIBLE);
            holder.count.setVisibility(View.VISIBLE);
        } else {
            // 他のアイテムにはボタンを非表示
            holder.btnAdd.setVisibility(View.GONE);
            holder.btnSubtract.setVisibility(View.GONE);
            holder.count.setVisibility(View.GONE);
        }

        // プラスボタンとマイナスボタンのクリックリスナーを設定
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = sharedViewModel.getMoneyCount().getValue()+1;
                sharedViewModel.setMoneyCount(currentCount);
                sharedViewModel.updateVictoryPoint(itemList);
                holder.count.setText("金 : "+ currentCount);
            }
        });

        holder.btnSubtract.setOnClickListener(v -> {
            int currentCount = sharedViewModel.getMoneyCount().getValue()-1;
            if(currentCount>=0)sharedViewModel.setMoneyCount(currentCount);
            sharedViewModel.updateVictoryPoint(itemList);
            holder.count.setText("金 : "+currentCount);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, count;
        Button btnAdd, btnSubtract;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemName);
            count = itemView.findViewById(R.id.bankcount);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnSubtract = itemView.findViewById(R.id.btnSubtract);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
