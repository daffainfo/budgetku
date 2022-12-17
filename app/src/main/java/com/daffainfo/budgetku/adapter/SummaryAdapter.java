package com.daffainfo.budgetku.adapter;

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

import com.daffainfo.budgetku.DetailActivity;
import com.daffainfo.budgetku.R;
import com.daffainfo.budgetku.model.Budget;

import java.util.List;

public class SummaryAdapter extends ArrayAdapter<Budget> {

    public static class ViewHolder {
        private TextView txt_categories, txt_nominal;
        private LinearLayout linear_layout;
    }

    public SummaryAdapter(Context context, int resource, List<Budget> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Budget budget = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.summary_listview, parent, false);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_categories = convertView.findViewById(R.id.txt_categories);
        viewHolder.txt_nominal = convertView.findViewById(R.id.txt_nominal);
        viewHolder.linear_layout = convertView.findViewById(R.id.history_listview);

        viewHolder.txt_categories.setText(budget.getCategories());
        viewHolder.txt_nominal.setText("Rp " + budget.getMoney());

        return convertView;
    }
}