package com.ibecsystems.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TimeZone {
    private Long zipCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date localTime;
    private float utcOffset;
    private String zone;

    public TimeZone(Long zipCode) {
        this.zipCode = zipCode;
    }

}
