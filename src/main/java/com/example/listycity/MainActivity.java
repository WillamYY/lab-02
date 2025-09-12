package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText editTextCity;
    Button buttonAdd, buttonDelete, buttonConfirm;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        cityList = findViewById(R.id.city_list);
        editTextCity = findViewById(R.id.editTextCity);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        // Initial data
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // Adapter setup
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Show input field when ADD CITY is clicked
        buttonAdd.setOnClickListener(v -> {
            editTextCity.setVisibility(View.VISIBLE);
            buttonConfirm.setVisibility(View.VISIBLE);
        });

        // Confirm adding city
        buttonConfirm.setOnClickListener(v -> {
            String cityName = editTextCity.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                editTextCity.setText("");
                editTextCity.setVisibility(View.GONE);
                buttonConfirm.setVisibility(View.GONE);
                Toast.makeText(this, "City added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Select city from list
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            for (int i = 0; i < cityList.getChildCount(); i++) {
                cityList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            }
            view.setBackgroundColor(Color.LTGRAY);
        });

        // Delete selected city
        buttonDelete.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                String removedCity = dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
                Toast.makeText(this, removedCity + " deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Select a city to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

}