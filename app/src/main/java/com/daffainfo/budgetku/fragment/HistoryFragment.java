package com.daffainfo.budgetku.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.daffainfo.budgetku.R;
import com.daffainfo.budgetku.adapter.HistoryAdapter;
import com.daffainfo.budgetku.model.Budget;
import com.daffainfo.budgetku.repository.BudgetRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements View.OnClickListener {
    private BudgetRepository budgetRepository;
    private HistoryAdapter historyAdapter;
    private FloatingActionButton floatingActionButton;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        budgetRepository = BudgetRepository.getInstance(this.getContext());
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        listView = view.findViewById(R.id.listview_history);
        floatingActionButton = view.findViewById(R.id.fab);

        ArrayList<Budget> budgets = budgetRepository.read();
        historyAdapter = new HistoryAdapter(getActivity(), R.id.history_listview, budgets);
        listView.setAdapter(historyAdapter);

        floatingActionButton.setOnClickListener(this);

        return view;
    }

    private View.OnClickListener showSearch() {
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        View view = inflater.inflate(R.layout.search_fab, null);

        AlertDialog.Builder add_contact_dialog = new AlertDialog.Builder(this.getActivity());

        add_contact_dialog.setView(view);

        EditText editText = view.findViewById(R.id.search_input);

        add_contact_dialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().length() == 0) {
                    ArrayList<Budget> budgets = budgetRepository.read();
                    historyAdapter = new HistoryAdapter(getActivity(), R.id.history_listview, budgets);
                    listView.setAdapter(historyAdapter);
                } else {
                    ArrayList<Budget> budgets = budgetRepository.search(editText.getText().toString());
                    historyAdapter = new HistoryAdapter(getActivity(), R.id.history_listview, budgets);
                    listView.setAdapter(historyAdapter);
                    Toast.makeText(getContext(),"Search complete", Toast.LENGTH_LONG).show();
                }
            }
        });
        add_contact_dialog.setNegativeButton("Batal", null);
        add_contact_dialog.show();
        return null;
    }

    @Override
    public void onClick(View view) {
        showSearch();
    }
}