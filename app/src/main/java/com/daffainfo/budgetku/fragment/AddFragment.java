package com.daffainfo.budgetku.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.daffainfo.budgetku.R;
import com.daffainfo.budgetku.model.Budget;
import com.daffainfo.budgetku.repository.BudgetRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private BudgetRepository budgetRepository;

    private void saveIncome(String name, String categories, String money, String description, String type, String datetime) {
        budgetRepository.create(Budget.create(name, categories, money, description, type, datetime));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        budgetRepository = BudgetRepository.getInstance(this.getContext());
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.spinner);
        EditText edit_nama = view.findViewById(R.id.edit_nama);
        EditText edit_kategori = view.findViewById(R.id.edit_kategori);
        EditText edit_nominal = view.findViewById(R.id.edit_nominal);
        EditText edit_desc = view.findViewById(R.id.edit_deskripsi);
        Button button = view.findViewById(R.id.buttonSubmit);
        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        autoCompleteTextView.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Income");
        categories.add("Expenses");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        autoCompleteTextView.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("type", autoCompleteTextView.getText().toString());
                if (edit_nama.getText().length() == 0 || edit_kategori.getText().length() == 0|| edit_nominal.getText().length() == 0|| edit_desc.getText().length() == 0 || autoCompleteTextView.getText().length() == 0) {
                    Toast.makeText(view.getContext(),"Fill all the blank",Toast.LENGTH_SHORT).show();
                } else if (autoCompleteTextView.getText().equals("Income") || autoCompleteTextView.getText().equals("Expenses")) {
                    Toast.makeText(view.getContext(),"Choose between income or expense",Toast.LENGTH_SHORT).show();
                } else {
                    saveIncome(edit_nama.getText().toString(), edit_kategori.getText().toString(), edit_nominal.getText().toString(), edit_desc.getText().toString(), autoCompleteTextView.getText().toString(), currentDateandTime);
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
//        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}