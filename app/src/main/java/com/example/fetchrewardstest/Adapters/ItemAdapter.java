package com.example.fetchrewardstest.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchrewardstest.Models.Item;
import com.example.fetchrewardstest.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<Item> itemList;
    private List<Integer> listIds;
    public static final String TAG = "ItemAdapter";

    public ItemAdapter(Context context, List<Item> itemList, List<Integer> listIds) {
        this.context = context;
        this.itemList = itemList;
        this.listIds = listIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer item = listIds.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + listIds.size());
        return listIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemListId;
        private RecyclerView rvNested;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemListId = itemView.findViewById(R.id.tvItemListId);
            rvNested = itemView.findViewById(R.id.rvNested);

        }

        public void bind(Integer item) {
            tvItemListId.setText("List ID: " + item);

            List<Item> idItemList = new ArrayList<>();
            for (int i = 0; i < itemList.size(); i++) {
                if (item.equals(itemList.get(i).getItemListID())) {
                    idItemList.add(itemList.get(i));
                }
            }

            ChildAdapter childAdapter = new ChildAdapter(idItemList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            rvNested.setLayoutManager(linearLayoutManager);
            rvNested.setAdapter(childAdapter);



        }
    }


}
