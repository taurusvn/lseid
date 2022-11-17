package com.dungc.ltc.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dungc.ltc.databinding.ItemNotifyBinding;
import com.dungc.ltc.model.Notify;

import java.util.ArrayList;
import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.NotifyViewHolder> {
    private final List<Notify> notifications = new ArrayList<>();

    public NotifyAdapter() {

    }

    @NonNull
    @Override
    public NotifyAdapter.NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifyViewHolder(ItemNotifyBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyAdapter.NotifyViewHolder holder, int position) {
        holder.bindData(notifications.get(position));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void initDatas(List<Notify> datas) {
        notifications.clear();
        notifications.addAll(datas);
        notifyDataSetChanged();
    }

    public static class NotifyViewHolder extends RecyclerView.ViewHolder {
        private final ItemNotifyBinding binding;

        public NotifyViewHolder(ItemNotifyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Notify obj) {
            binding.tvTitle.setText(obj.title);
            binding.tvContent.setText(obj.message);
            binding.tvTime.setText(obj.timeCreated);
        }
    }
}
