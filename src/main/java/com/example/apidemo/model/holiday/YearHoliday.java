package com.example.apidemo.model.holiday;

import lombok.Data;

import java.util.List;

@Data
public class YearHoliday {

    Integer year;
    List<HolidayDetail> holidays;
}
