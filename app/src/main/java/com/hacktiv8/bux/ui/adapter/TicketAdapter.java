package com.hacktiv8.bux.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hacktiv8.bux.databinding.OrderBinding;
import com.hacktiv8.bux.model.Bus;
import com.hacktiv8.bux.model.Order;
import com.hacktiv8.bux.model.Ticket;
import com.hacktiv8.bux.model.Trip;
import com.hacktiv8.bux.ui.ticket.OrderDetailActivity;
import com.hacktiv8.bux.utils.DateHelper;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
    private Context context;
    private List<Order> list;
    private FirebaseFirestore db;
    private Trip trip;
    private Bus bus;

    public TicketAdapter(Context context, List<Order> list, FirebaseFirestore db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderBinding binding = OrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order order = list.get(position);
        if (order != null){
            holder.bind(order);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final OrderBinding binding;

        public MyViewHolder(OrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Order order) {
            getBusData(order.getPlatno());
            getTripData(order.getIdTrip());

            binding.idTicket.setText(order.getBookNo());
            binding.platBus.setText(order.getPlatno());
            binding.status.setText(order.getStatus());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra(OrderDetailActivity.EXTRA_BOOK_NO, order.getBookNo());
                intent.putExtra(OrderDetailActivity.EXTRA_TRIP_ID, order.getIdTrip());
                intent.putExtra(OrderDetailActivity.EXTRA_BUS_NO, order.getPlatno());
                intent.putExtra(OrderDetailActivity.EXTRA_RATING_STATE, order.isRated());
                intent.putExtra(OrderDetailActivity.EXTRA_STATUS, order.getStatus());
                intent.putExtra(OrderDetailActivity.EXTRA_BUS_NAME, bus.getBusName());
                intent.putExtra(OrderDetailActivity.EXTRA_DEPART_DATE, DateHelper.timestampToLocalDate4(trip.getDate()));
                itemView.getContext().startActivity(intent);
            });
        }

        public void bindTripData(Trip trip) {
            if (trip!=null) {
                binding.tgl.setText(DateHelper.timestampToLocalDate3(trip.getDate()));
                binding.jam.setText(trip.getDepartHour());
                binding.terminalBus.setText(trip.getDepartTerminal());
                binding.destBus.setText(trip.getArrivalTerminal());
                binding.timeBus.setText(trip.getEtaJam());
            }
        }

        public void bindBusData (Bus bus) {
            binding.nameBus.setText(bus.getBusName());
        }

        private void getTripData(String tripId) {
            db.collection("trip")
                    .whereEqualTo("idTrip", tripId)
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                trip = document.toObject(Trip.class);
                                bindTripData(trip);
                            }
                        } else {
                            Toast.makeText(context ,"Failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void getBusData(String platBus) {

            db.collection("bus")
                    .whereEqualTo("platno", platBus)
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                bus = document.toObject(Bus.class);
                                bindBusData(bus);
                            }
                        } else {
                            Toast.makeText(context ,"Failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


}
