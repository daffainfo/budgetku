package com.daffainfo.budgetku.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.daffainfo.budgetku.R;
import com.daffainfo.budgetku.adapter.SummaryAdapter;
import com.daffainfo.budgetku.model.Budget;
import com.daffainfo.budgetku.repository.BudgetRepository;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummaryFragment extends Fragment {
    private BudgetRepository budgetRepository;
    private SummaryAdapter summaryAdapter, summaryAdapter2;
    private ListView listView, listView2;
    private PieChart pieChart;

    private double count(String type) { return budgetRepository.count(type); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        budgetRepository = BudgetRepository.getInstance(this.getContext());
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        listView = view.findViewById(R.id.listview_summary1);
        listView2 = view.findViewById(R.id.listview_summary2);
        pieChart = view.findViewById(R.id.chart);

        ArrayList<Budget> budget = budgetRepository.topCategories("Income");
        summaryAdapter = new SummaryAdapter(getActivity(), R.id.summary_listview, budget);
        listView.setAdapter(summaryAdapter);

        ArrayList<Budget> budget2 = budgetRepository.topCategories("Expenses");
        summaryAdapter2 = new SummaryAdapter(getActivity(), R.id.summary_listview, budget2);
        listView2.setAdapter(summaryAdapter2);

        initPieChart();
        return view;
    }

    private void initPieChart(){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(true);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setRotationAngle(0);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = pieChart.getLegend();
        l.setEnabled(false);

        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Pemasukan", (int) count("Income"));
        typeAmountMap.put("Pengeluaran",(int) count("Expenses"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#02cc6c"));
        colors.add(Color.parseColor("#ad1c1c"));

        for(String type: typeAmountMap.keySet()){
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries,null);
        pieDataSet.setValueTextSize(13f);
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}