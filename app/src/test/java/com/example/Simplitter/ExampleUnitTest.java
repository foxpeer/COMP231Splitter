/*
 * Author:Xinglong Lu. Last Modify: 28, Nov, 2020
 * */
package com.example.Simplitter;

import com.example.Simplitter.Activity.CalculateActivity;
import com.example.Simplitter.Activity.ExpensesDetailActivity;
import com.example.Simplitter.Activity.RegisterActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {

    @Test
    public void roundInput()
    {
        double result=CalculateActivity.round(255.63,2);
        assertThat(result,is(equalTo(255.63)));
    }

    @Test
    public void splitResult(){
        double result= ExpensesDetailActivity.getResult(335.25,3);
        assertThat(result,is(equalTo(112.0)));
    }

    @Test
    public void validateEmailInputFormat(){
        RegisterActivity activity=new RegisterActivity();
        String email ="test123";
        boolean result=activity.validateEmailInputFormat(email);
        assertThat(result,is(equalTo(false)));
    }
}