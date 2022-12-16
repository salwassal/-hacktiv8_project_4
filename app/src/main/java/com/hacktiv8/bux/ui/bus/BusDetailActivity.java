package com.hacktiv8.bux.ui.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktiv8.bux.R;
import com.hacktiv8.bux.databinding.ActivityBusDetailBinding;
import com.hacktiv8.bux.model.Bus;
import com.hacktiv8.bux.model.Trip;
import com.hacktiv8.bux.utils.DateHelper;

public class BusDetailActivity extends AppCompatActivity {

    private ActivityBusDetailBinding binding;
    private FirebaseFirestore db;
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_BUS_NO = "extra_bus_no";
    private Trip trip;
    private Bus bus;
    private String tripId, platBus, imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();

        tripId = getIntent().getStringExtra(EXTRA_ID);
        platBus = getIntent().getStringExtra(EXTRA_BUS_NO);
        if (tripId==null) {
            Toast.makeText(this ,"Failed to get data", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (platBus==null) {
            Toast.makeText(this ,"Failed to get data", Toast.LENGTH_SHORT).show();
            finish();
        }

        getBusData(platBus);
        getTripData(tripId);

        binding.backbutton.setOnClickListener(v -> {
            finish();
        });


        binding.btnSeePicture.setOnClickListener(v -> {
            if (imgUrl!=null) {
                onImageShow(imgUrl);
            }
        });

        binding.btnBookNow.setOnClickListener(v -> {
            Intent intent = new Intent(this, SeatChooserActivity.class);
            intent.putExtra(SeatChooserActivity.EXTRA_TRIP_ID, trip.getIdTrip());
            intent.putExtra(SeatChooserActivity.EXTRA_BUS_NO, trip.getPlatBus());
            startActivity(intent);
        });

    }

    private void onImageShow(String uri) {
        View inflatedView = getLayoutInflater().inflate(R.layout.img_view_layout, null);
        ImageView imageView = inflatedView.findViewById(R.id.ivViewer);
        Glide.with(this)
                .load(uri)
                .placeholder(R.drawable.bux_logo)
                .into(imageView);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setView(inflatedView).show();
    }

    private void getTripData(String tripId) {
        db.collection("trip")
                .whereEqualTo("idTrip", tripId)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            trip = document.toObject(Trip.class);
                            populateTripData(trip);
                        }
                    } else {
                        Toast.makeText(this ,"Failed to get data", Toast.LENGTH_SHORT).show();
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
                            populateBusData(bus);

                        }
                    } else {
                        Toast.makeText(this ,"Failed to get data", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void populateTripData(Trip tripData) {
        if (tripData!=null) {
            binding.departDate.setText(
                    DateHelper.timestampToLocalDate(trip.getDate())
            );
            binding.departCity.setText(tripData.getDepartCity());
            binding.departTime.setText(tripData.getDepartHour());
            binding.tvDate.setText(
                    DateHelper.timestampToLocalDate2(trip.getDate())
            );
            binding.destinationCity.setText(tripData.getArrivalCity());
            binding.destinationTime.setText(tripData.getArrivalHour());
            binding.tvCalculation.setText("1 x " + tripData.getPrice());
            binding.tvFinalPrice.setText("Rp " + tripData.getPrice());
        } else {
            Toast.makeText(this ,"Failed to get data", Toast.LENGTH_SHORT).show();
        }

    }

    private void populateBusData(Bus busData) {
        if (busData!=null) {
            binding.nameBus.setText(busData.getBusName());
            binding.tvBusNo.setText(busData.getPlatno());
            binding.tvSeat.setText(busData.getAvailableSeats()+" Seat are available");
            binding.tvRating.setText(busData.getRating()+"/10");

            imgUrl = busData.getImgUrl();
            Glide.with(this)
                    .load(busData.getImgUrl())
                    .centerCrop()
                    .into(binding.ivBus);
        } else {
            Toast.makeText(this ,"Failed to get data", Toast.LENGTH_SHORT).show();
        }

    }


}