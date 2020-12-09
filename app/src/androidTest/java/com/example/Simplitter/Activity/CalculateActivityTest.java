package com.example.Simplitter.Activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.test.*;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.Simplitter.R;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CalculateActivityTest {

    @Rule
    public ActivityTestRule<CalculateActivity> mActivityTestRule=new ActivityTestRule<CalculateActivity>(CalculateActivity.class);
    private Intent mCalculateIntent;
    private CalculateActivity mActivity=null;

    public CalculateActivityTest(){

    }

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testResultView() {
        View view=mActivity.findViewById(R.id.textView_result);

        assertNotNull(view);
    }
    @Test
    public void testInputAmount(){
        View view=mActivity.findViewById(R.id.editText_amount);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception{
        mActivity=null;
    }

}