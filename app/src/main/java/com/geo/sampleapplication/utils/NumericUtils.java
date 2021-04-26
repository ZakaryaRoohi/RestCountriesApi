package com.geo.sampleapplication.utils;

public class NumericUtils {

    //trim method
     public static double trimInputTo2DecimalPlace(double value) {
        value = value * Math.pow(10, 2);
        value = Math.floor(value);
        value = value / Math.pow(10, 2);

        return value;
    }
}
