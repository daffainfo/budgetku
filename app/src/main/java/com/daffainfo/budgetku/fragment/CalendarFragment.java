package com.daffainfo.budgetku.fragment;

import android.graphics.Color;
import android.media.metrics.Event;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.daffainfo.budgetku.R;
import com.daffainfo.budgetku.repository.BudgetRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarFragment extends Fragment {
    private BudgetRepository budgetRepository;

//    private double budgetDate(String type, String datetime) { return budgetRepository.budgetDate(datetime, type); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        budgetRepository = BudgetRepository.getInstance(this.getContext());
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        TextView textIncome = view.findViewById(R.id.txt_income);
        TextView textExpenses = view.findViewById(R.id.txt_expense);
        // Set an OnDateChangeListener to handle date changes
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month + 1) + "-" + String.format("%02d", dayOfMonth);
                Toast.makeText(getContext(), "Selected date: " + date, Toast.LENGTH_SHORT).show();
//                Log.d("testBudget", String.valueOf(budgetRepository.budgetDate("Income", date)));
//                Log.d("testBudget", String.valueOf(budgetRepository.budgetDate("Expenses", date)));
                int income = (int) budgetRepository.budgetDate("Income", date);
                int expenses = (int) budgetRepository.budgetDate("Expenses", date);;
                if (income == 0) {
                    textIncome.setTextColor(Color.GRAY);
                    textIncome.setText("Rp 0");
                } else {
                    textIncome.setTextColor(Color.GREEN);
                    textIncome.setText("Rp " + String.valueOf((int) budgetRepository.budgetDate("Income", date)));
                }
//
                if (expenses == 0) {
                    textExpenses.setTextColor(Color.GRAY);
                    textExpenses.setText("Rp 0");
                } else {
                    textExpenses.setTextColor(Color.RED);
                    textExpenses.setText("Rp " + String.valueOf((int) budgetRepository.budgetDate("Expenses", date)));
                }
            }

        });

        return view;
    }
}