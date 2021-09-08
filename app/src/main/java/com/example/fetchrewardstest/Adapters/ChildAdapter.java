package com.example.fetchrewardstest.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchrewardstest.Models.Item;
import com.example.fetchrewardstest.R;

import java.util.ArrayList;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    private List<Item> childItemArrayList;

    public ChildAdapter(List<Item> childItemArrayList) {
        this.childItemArrayList = childItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = childItemArrayList.get(position);
        holder.bind(item);
    }   

    @Override
    public int getItemCount() {
        return childItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvId);
            itemName = itemView.findViewById(R.id.tvItemName);

        }

        public void bind(Item item) {
            id.setText("ID: " + String.valueOf(item.getItemID()));
            itemName.setText("Item " + String.valueOf(item.getItemName()));
        }
    }
}
