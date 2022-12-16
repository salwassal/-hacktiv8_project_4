package com.hacktiv8.bux.ui.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hacktiv8.bux.R;
import com.hacktiv8.bux.databinding.ActivityBankTransferBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class BankTransferActivity extends AppCompatActivity {

    private ActivityBankTransferBinding binding;
    private String total, tripId, platBus, bookedSeat, toTgl;
    public static final String EXTRA_TRIP_ID = "extra_tripid";
    public static final String EXTRA_BUS_NO = "extra_busno";
    public static final String EXTRA_BOOKED_SEAT = "extra_booked_seat";
    public static final String EXTRA_TOTAL = "extra_total";
    public static final String EXTRA_TO_TGL = "extra_to_tgl";
    private Locale localeID = new Locale("in", "ID");
    private NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBankTransferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tripId = getIntent().getStringExtra(EXTRA_TRIP_ID);
        platBus = getIntent().getStringExtra(EXTRA_BUS_NO);
        bookedSeat = getIntent().getStringExtra(EXTRA_BOOKED_SEAT);
        total = getIntent().getStringExtra(EXTRA_TOTAL);
        toTgl = getIntent().getStringExtra(EXTRA_TO_TGL);
        double dTotal = Double.valueOf(total);
        binding.totalPaymentTv.setText(formatRupiah.format((double)dTotal));

        binding.bniCv.setOnClickListener(v ->{
            startActivity(new Intent(this, BankTransferVerificationActivity.class)
                    .putExtra(BankTransferVerificationActivity.EXTRA_TRIP_ID, tripId)
                    .putExtra(BankTransferVerificationActivity.EXTRA_BUS_NO, platBus)
                    .putExtra(BankTransferVerificationActivity.EXTRA_BOOKED_SEAT, bookedSeat)
                    .putExtra(BankTransferVerificationActivity.EXTRA_TOTAL, total)
                    .putExtra(BankTransferVerificationActivity.EXTRA_TO_TGL, toTgl)
            );
        });

        binding.cimbCv.setOnClickListener(v ->{
            Toast.makeText(this, "Sorry, transaction at bank cimbNiaga Error", Toast.LENGTH_SHORT).show();
        });

    }
}