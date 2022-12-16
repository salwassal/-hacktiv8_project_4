package com.hacktiv8.bux.ui.chooser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hacktiv8.bux.R;
import com.hacktiv8.bux.databinding.ActivityDatePickerBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {

    private ActivityDatePickerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatePickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        binding.tvSelectedDate.setText(dateFormat.format(calendar.getTime()));

        binding.calenderView.setOnDateChangeListener((calendarView, y, m, d) -> {
            calendar.set(y, m, d);
            binding.tvSelectedDate.setText(dateFormat.format(calendar.getTime()));
        });

        binding.btnSearch.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("date", calendar).putExtra("dtimeInMillis", calendar.getTimeInMillis()));
            onBackPressed();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}