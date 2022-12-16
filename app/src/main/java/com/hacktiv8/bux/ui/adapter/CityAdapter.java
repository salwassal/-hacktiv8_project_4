package com.hacktiv8.bux.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.bux.databinding.ItemDestinationSearchBinding;
import com.hacktiv8.bux.model.City;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    public ItemClickListener itemClickListener;
    private Context context;
    private ArrayList<City> list;

    public CityAdapter(Context context, ArrayList<City> list){
        this.context = context;
        this.list = list;
    }

    public void setFilteredList(Context context, ArrayList<City> filteredList){
        this.context = context;
        this.list = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDestinationSearchBinding binding = ItemDestinationSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.MyViewHolder holder, int position) {
        City city = list.get(position);
        if(city!= null){
            holder.bind(city);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemDestinationSearchBinding binding;


        public MyViewHolder(ItemDestinationSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(City city){
            binding.tvKota.setText(city.getCity());
            binding.tvTerminal.setText("Terminal " + city.getTerminal());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(list.get(getAdapterPosition()));
                }
            });


        }

    }

    public interface ItemClickListener{
        void onClick(City city);
    }
}
