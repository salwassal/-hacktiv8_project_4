package com.hacktiv8.bux.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hacktiv8.bux.databinding.ActivityBusDetailBinding;
import com.hacktiv8.bux.databinding.ItemTripTicketLayoutBinding;
import com.hacktiv8.bux.model.Trip;
import com.hacktiv8.bux.ui.bus.BusDetailActivity;
import com.hacktiv8.bux.ui.bus.BusScheduleActivity;
import com.hacktiv8.bux.ui.bus.SeatChooserActivity;
import com.hacktiv8.bux.utils.DateHelper;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {

    private Context context;
    private List<Trip> list;

    public TripAdapter(Context context, List<Trip> list){
        this.context = context;
        this.list = list;
    }

    public void setFilteredList(Context context, ArrayList<Trip> filteredList){
        this.context = context;
        this.list = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TripAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTripTicketLayoutBinding binding = ItemTripTicketLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.MyViewHolder holder, int position) {
        Trip trip = list.get(position);
        if(trip!= null){
            holder.bind(trip);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemTripTicketLayoutBinding binding;

        public MyViewHolder(ItemTripTicketLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Trip trip) {
            binding.tvBusName.setText(trip.getBusName());
            binding.tvBusNo.setText(trip.getPlatBus());
            binding.tvDepartDate.setText(
                    DateHelper.timestampToLocalDate(trip.getDate())
            );
            binding.tvDepartHour.setText(trip.getDepartHour());
            binding.tvDepartCity.setText(trip.getDepartCity());
            binding.tvDepartStation.setText("Terminal "+trip.getDepartTerminal());
            binding.tvArriveHour.setText(trip.getArrivalHour());
            binding.tvRating.setText(trip.getRating() + "/5");
            binding.tvArriveCity.setText(trip.getArrivalCity());
            binding.tvArriveStation.setText("Terminal "+trip.getArrivalTerminal());
            binding.tvPrice.setText("Rp."+trip.getPrice());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, BusDetailActivity.class);
                intent.putExtra(BusDetailActivity.EXTRA_ID, trip.getIdTrip());
                intent.putExtra(BusDetailActivity.EXTRA_BUS_NO, trip.getPlatBus());
                itemView.getContext().startActivity(intent);
            });

            binding.btnBookNow.setOnClickListener(v -> {
                Intent intent = new Intent(context, SeatChooserActivity.class);
                intent.putExtra(SeatChooserActivity.EXTRA_TRIP_ID, trip.getIdTrip());
                intent.putExtra(SeatChooserActivity.EXTRA_BUS_NO, trip.getPlatBus());
                itemView.getContext().startActivity(intent);
            });

        }

    }


}
