package com.hacktiv8.bux.ui.home;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.hacktiv8.bux.R;
import com.hacktiv8.bux.databinding.FragmentHomeBinding;
import com.hacktiv8.bux.model.City;
import com.hacktiv8.bux.ui.bus.BusScheduleActivity;
import com.hacktiv8.bux.ui.chooser.DatePickerActivity;
import com.hacktiv8.bux.ui.chooser.DestinationChooserActivity;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SimpleDateFormat format;
    private Calendar calendar;
    private City departureCity;
    private  City arrivalCity;
    private int valuePassanger;
    private int lastValuePassanger;
    private long timeInMillis;

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        format = new SimpleDateFormat(" dd MMM");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        Intent intent = new Intent(getContext(), DestinationChooserActivity.class);
        binding.passangers.setOnClickListener(v ->{
            showSheetSlider();
        });
        binding.date.setOnClickListener(v ->{
            startActivityForResult(new Intent(getContext(), DatePickerActivity.class), 1);
        });
        binding.departure.setOnClickListener(v ->{
            startActivityForResult(intent, 2);
        });
        binding.arrival.setOnClickListener(v ->{
            startActivityForResult(intent, 3);
        });

        binding.btnSearch.setOnClickListener(v ->{
            onSearchActivity();
        });

        if(savedInstanceState != null){
            onStateData(savedInstanceState);
        }

        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("departure", departureCity);
        outState.putParcelable("arrival", arrivalCity);
        outState.putSerializable("date", calendar);
//        outState.putString("passengers", binding.passangers.getText().toString());
    }

    private void onStateData(Bundle savedInstanceState) {
        departureCity = savedInstanceState.getParcelable("departure");
        arrivalCity = savedInstanceState.getParcelable("arrival");
        calendar = (Calendar) savedInstanceState.getSerializable("date");
//        String passengers = savedInstanceState.getString("passengers");

        if(calendar!=null) binding.date.setText(format.format(calendar.getTime()));
        if(departureCity!=null) binding.departure.setText(departureCity.getCity());
        if(arrivalCity!=null) binding.arrival.setText(arrivalCity.getCity());

//        binding.passangers.setText(passengers);
    }

    private void onSearchActivity() {
        if(binding.departure.getText().toString().length() == 0 || binding.arrival.getText().toString().length() == 0 || binding.date.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "complete the data please", Toast.LENGTH_SHORT).show();
        }else if(binding.departure.getText().toString().equals(binding.arrival.getText().toString())){
            Toast.makeText(getActivity(), "departure and arrival cannot same please", Toast.LENGTH_SHORT).show();
        }else {
            startActivity(new Intent(getContext(), BusScheduleActivity.class)
                    .putExtra("departure", departureCity)
                    .putExtra("arrival", arrivalCity)
                    .putExtra("date", calendar)
                    .putExtra("timeInMillis", timeInMillis)
                    .putExtra("passengers", binding.passangers.getText().toString().trim())
            );
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(data != null && resultCode == RESULT_OK){
                    calendar = (Calendar) data.getSerializableExtra("date");
                    timeInMillis = (long) data.getSerializableExtra("dtimeInMillis");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM");
                    binding.date.setText(simpleDateFormat.format(calendar.getTime()));
                }
                break;
            case 2:
                if(data != null && resultCode == RESULT_OK){
                    departureCity = data.getParcelableExtra("city");
                    binding.departure.setText(departureCity.getCity());
                }
                break;
            case 3:
                if(data != null && resultCode == RESULT_OK){
                    arrivalCity = data.getParcelableExtra("city");
                    binding.arrival.setText(arrivalCity.getCity());
                }
                break;
        }

    }

    private void showSheetSlider() {

        final Dialog dialog = new Dialog(requireActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_slider_passanger);

        TextView totalSeat = dialog.findViewById(R.id.tvNumberSlider);

        Slider  slider = dialog.findViewById(R.id.sliderPassanger);
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                valuePassanger = (int) value;
                lastValuePassanger = (int) value;
                totalSeat.setText(String.valueOf(valuePassanger));
            }
        });

        dialog.findViewById(R.id.btnCancelPassanger).setOnClickListener(v -> {
            valuePassanger = 0;
            dialog.dismiss();
        });

        dialog.findViewById(R.id.btnSelect).setOnClickListener(v -> {
            binding.passangers.setText(String.valueOf(lastValuePassanger));
            dialog.dismiss();
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}