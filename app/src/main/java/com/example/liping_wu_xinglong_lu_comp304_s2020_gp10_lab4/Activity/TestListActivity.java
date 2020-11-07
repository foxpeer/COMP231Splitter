package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Test;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Adapter.TestAdapter;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.TestViewModel;

import java.util.List;

public class TestListActivity extends AppCompatActivity {
    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlist);
        setTitle("All Tests");
      //Receive the patientID from patientlist to filter tests with this patientID
        Bundle extras = getIntent().getExtras();
        int patientID = extras.getInt("PATIENTID");
        Log.d("TestListActivity:: onCreate(): ","patientID: "+ patientID);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TestAdapter adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.getAllTest(patientID).observe(this, new Observer<List<Test>>() {

            @Override
            public void onChanged(List<Test> tests) {
                //update RecycleView
                adapter.setTests(tests);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                testViewModel.delete(adapter.getTestAct(viewHolder.getAdapterPosition()));
                Toast.makeText(TestListActivity.this, "Test deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void BackToPatient(View view){
        finish();
    }
}

