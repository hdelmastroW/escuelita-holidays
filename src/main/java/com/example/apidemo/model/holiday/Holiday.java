package com.example.apidemo.model.holiday;

import lombok.Data;

import java.util.List;

@Data
public class Holiday {

    String country;
    List<YearHoliday> yearHolidays;
}
