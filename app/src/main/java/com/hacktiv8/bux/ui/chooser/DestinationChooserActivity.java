package com.hacktiv8.bux.ui.chooser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.hacktiv8.bux.R;
import com.hacktiv8.bux.databinding.ActivityDestinationChooserBinding;
import com.hacktiv8.bux.model.City;
import com.hacktiv8.bux.ui.adapter.CityAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DestinationChooserActivity extends AppCompatActivity {

    private RecyclerView rvCity;
    private ArrayList<City> list = new ArrayList<>();
    private CityAdapter cityAdapter;
    private ActivityDestinationChooserBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDestinationChooserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvCity = binding.recyclerView;
        rvCity.setHasFixedSize(true);
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        // add data
        list.add(new City("1","Semarang", "Mangkang"));
        list.add(new City("2","Surabaya", "Bungurasih"));
        list.add(new City("3","Bandung", "Leuwi Panjang"));
        list.add(new City("4","Jakarta", "Senen"));

        data();

        binding.edSearch.setFocusable(true);
        binding.tvCancel.setOnClickListener(v -> onBackPressed());
        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String newText = String.valueOf(binding.edSearch.getText());
                filterList(newText);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void filterList(String text) {
        ArrayList<City> filteredList = new ArrayList<>();
        for(City city : list){
            if(city.getCity().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(city);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else {
            cityAdapter.setFilteredList(DestinationChooserActivity.this, filteredList);
        }
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



    private void data(){
        cityAdapter = new CityAdapter(DestinationChooserActivity.this, list);
        rvCity.setAdapter(cityAdapter);

        cityAdapter.setItemClickListener(new CityAdapter.ItemClickListener() {
            @Override
            public void onClick(City city) {
                setResult(RESULT_OK, new Intent()
                        .putExtra("city", city)
                );
                onBackPressed();
            }
        });

    }

}