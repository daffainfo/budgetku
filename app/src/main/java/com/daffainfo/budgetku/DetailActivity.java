package com.daffainfo.budgetku;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.daffainfo.budgetku.model.Budget;
import com.daffainfo.budgetku.repository.BudgetRepository;

import java.util.StringTokenizer;

public class DetailActivity extends AppCompatActivity {
    private BudgetRepository budgetRepository;

    private LinearLayout hiddenView;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int id = getIntent().getIntExtra("id", 0);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.title);
        TextView txt_nama = findViewById(R.id.txt_nama);
        TextView txt_categories = findViewById(R.id.txt_categories);
        TextView txt_money = findViewById(R.id.txt_money);
        TextView txt_date = findViewById(R.id.txt_date);
        TextView txt_time = findViewById(R.id.txt_time);
        TextView txt_description = findViewById(R.id.txt_description);

        cardView = findViewById(R.id.cardview);
        ImageButton arrow = findViewById(R.id.arrow);
        hiddenView = findViewById(R.id.hiddenview);

        budgetRepository = BudgetRepository.getInstance(this.getApplicationContext());
        Budget budget = budgetRepository.get(id);

        StringTokenizer tk = new StringTokenizer(budget.getDatetime());

        if (budget.getType().equals("Income")) {
            title.setText("Pemasukan");
            title.setTextColor(Color.GREEN);
        } else {
            title.setText("Pengeluaran");
            title.setTextColor(Color.RED);
        }

        txt_nama.setText(budget.getName());
        txt_categories.setText(budget.getCategories());
        txt_money.setText("Rp " + budget.getMoney());
        txt_description.setText(budget.getDescription());
        txt_date.setText(tk.nextToken()); // yyyy-mm-dd
        txt_time.setText(tk.nextToken()); // hh:mm:ss

        hiddenView.setVisibility(View.GONE);

        arrow.setOnClickListener(view -> {
            if (hiddenView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.GONE);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
            }
        });

    }

}