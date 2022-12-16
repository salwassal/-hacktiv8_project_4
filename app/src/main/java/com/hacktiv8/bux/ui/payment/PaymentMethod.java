package com.hacktiv8.bux.ui.payment;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hacktiv8.bux.R;
import com.hacktiv8.bux.databinding.ActivityPaymentMethodBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentMethod extends AppCompatActivity {

    private ActivityPaymentMethodBinding binding;
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
        binding = ActivityPaymentMethodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tripId = getIntent().getStringExtra(EXTRA_TRIP_ID);
        platBus = getIntent().getStringExtra(EXTRA_BUS_NO);
        bookedSeat = getIntent().getStringExtra(EXTRA_BOOKED_SEAT);
        total = getIntent().getStringExtra(EXTRA_TOTAL);
        toTgl = getIntent().getStringExtra(EXTRA_TO_TGL);
        double dTotal = Double.valueOf(total);
        binding.totalPaymentTv.setText(formatRupiah.format((double)dTotal));
        binding.creditTv.setText(toTgl);

        binding.creditCv.setOnClickListener(v -> {
            startActivity(new Intent(this, CreditCardVerificationActivity.class)
                    .putExtra(CreditCardVerificationActivity.EXTRA_TRIP_ID, tripId)
                    .putExtra(CreditCardVerificationActivity.EXTRA_BUS_NO, platBus)
                    .putExtra(CreditCardVerificationActivity.EXTRA_BOOKED_SEAT, bookedSeat)
                    .putExtra(CreditCardVerificationActivity.EXTRA_TOTAL, total)
                    .putExtra(CreditCardVerificationActivity.EXTRA_TO_TGL, toTgl)
            );

        });

        binding.bankCv.setOnClickListener(v -> {
            startActivity(new Intent(this, BankTransferActivity.class)
                    .putExtra(BankTransferActivity.EXTRA_TRIP_ID, tripId)
                    .putExtra(BankTransferActivity.EXTRA_BUS_NO, platBus)
                    .putExtra(BankTransferActivity.EXTRA_BOOKED_SEAT, bookedSeat)
                    .putExtra(BankTransferActivity.EXTRA_TOTAL, total)
                    .putExtra(BankTransferActivity.EXTRA_TO_TGL, toTgl)
            );
        });

        binding.retailCv.setOnClickListener(v -> {
            startActivity(new Intent(this, RetailPaymentVerificationActivity.class)
                    .putExtra(RetailPaymentVerificationActivity.EXTRA_TRIP_ID, tripId)
                    .putExtra(RetailPaymentVerificationActivity.EXTRA_BUS_NO, platBus)
                    .putExtra(RetailPaymentVerificationActivity.EXTRA_BOOKED_SEAT, bookedSeat)
                    .putExtra(RetailPaymentVerificationActivity.EXTRA_TOTAL, total)
                    .putExtra(RetailPaymentVerificationActivity.EXTRA_TO_TGL, toTgl)
            );
        });



    }
}