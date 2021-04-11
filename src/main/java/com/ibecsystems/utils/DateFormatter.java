package com.ibecsystems.utils;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static Date getDateFromJson(JSONObject jsonObject, String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date result = sdf.parse(jsonObject.getString(date));
        return result;

    }
}
