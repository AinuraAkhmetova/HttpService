package com.ibecsystems.service.impl;

import com.ibecsystems.model.TimeZone;
import com.ibecsystems.service.TimeZoneService;
import com.ibecsystems.utils.DateFormatter;
import com.ibecsystems.utils.UrlParser;
import org.json.JSONObject;

import java.text.ParseException;

public class TimeZoneServiceImpl implements TimeZoneService {

    final String URL_PART_ONE = "https://api.worldweatheronline.com/premium/v1/tz.ashx?key=28d3e1910d624d03b71135816210904&q=";
    final String URL_PART_TWO = "&format=json";

    @Override
    public TimeZone getTimeZone(TimeZone timeZone) {

        String output = UrlParser.getUrlContent(URL_PART_ONE + timeZone.getZipCode() + URL_PART_TWO);

        if (!output.isEmpty()) {
            JSONObject object = new JSONObject(output).getJSONObject("data").getJSONArray("time_zone").getJSONObject(0);
            timeZone.setUtcOffset(object.getFloat("utcOffset"));
            timeZone.setZone(object.getString("zone"));
            try {
                timeZone.setLocalTime(DateFormatter.getDateFromJson(object, "localtime"));
            } catch (ParseException e) {
                timeZone.setLocalTime(null);
            }
        }

        return timeZone;
    }
}
