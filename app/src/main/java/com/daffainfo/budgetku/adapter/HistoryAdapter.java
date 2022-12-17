package com.daffainfo.budgetku.adapter;

import com.daffainfo.budgetku.DetailActivity;
import com.daffainfo.budgetku.fragment.HistoryFragment;
import com.daffainfo.budgetku.model.Budget;
import com.daffainfo.budgetku.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends ArrayAdapter<Budget> {

    public static class ViewHolder {
        private TextView txt_nama, txt_categories, txt_nominal;
        private LinearLayout linear_layout;
    }

    public HistoryAdapter(Context context, int resource, List<Budget> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Budget budget = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_listview, parent, false);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_nama = convertView.findViewById(R.id.txt_nama);
        viewHolder.txt_categories = convertView.findViewById(R.id.txt_categories);
        viewHolder.txt_nominal = convertView.findViewById(R.id.txt_nominal);
        viewHolder.linear_layout = convertView.findViewById(R.id.history_listview);

        viewHolder.txt_nama.setText(budget.getName());
        viewHolder.txt_categories.setText(budget.getCategories());
        viewHolder.txt_nominal.setText("Rp " + budget.getMoney());

        Log.d("cekType", budget.getType());

        if (budget.getType().equals("Income")) {
            viewHolder.txt_nominal.setTextColor(Color.GREEN);
        } else {
            viewHolder.txt_nominal.setTextColor(Color.RED);
        }
        viewHolder.linear_layout.setOnClickListener(
                view -> {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("id", budget.getId());
                    getContext().startActivity(intent);
                }
        );

        return convertView;
    }
}