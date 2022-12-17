package com.daffainfo.budgetku;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.daffainfo.budgetku.fragment.AddFragment;
import com.daffainfo.budgetku.fragment.CalendarFragment;
import com.daffainfo.budgetku.fragment.FaqFragment;
import com.daffainfo.budgetku.fragment.HistoryFragment;
import com.daffainfo.budgetku.fragment.SummaryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    SummaryFragment summaryFragment = new SummaryFragment();
    HistoryFragment historyFragment = new HistoryFragment();
    AddFragment addFragment = new AddFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    FaqFragment faqFragment = new FaqFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,summaryFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_summary:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,summaryFragment).commit();
                        return true;
                    case R.id.action_history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,historyFragment).commit();
                        return true;
                    case R.id.action_add:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,addFragment).commit();
                        return true;
                    case R.id.action_calendar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,calendarFragment).commit();
                        return true;
                    case R.id.action_faq:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,faqFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

}
