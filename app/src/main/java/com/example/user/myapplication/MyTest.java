package com.example.user.myapplication;

import android.util.Log;

/**
 * Created by user on 10/24/2017.
 */

public class MyTest {
    private static int iVal1 = 0;
    public static int iVal2 = 0;

    public MyTest() {
        iVal1 = 3;
        iVal2 = 22;
    }

    public MyTest(int iVal) {
        iVal1 = iVal;
        iVal2 = iVal * 2;

    }
    public static void showVals() {
        Log.d("BBB", "Val1=" + iVal1 + " Val2=" + iVal2);
    }
}
