package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.Simplitter.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ExpensesDetailActivity extends AppCompatActivity {

    // refer : http://www.devexchanges.info/2016/01/android-basic-course-creating-and.html
    // did not use action bar , related view: list_row_item & expense detail
    //
    ListView exdetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_detail);

        exdetailList = findViewById(R.id.lv_listexpense);
    }

    // paynow btn for payment option page
    public void PayNowClick(View view) {

    }
}