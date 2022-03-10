package com.example.apidemo.controller;

import com.example.apidemo.model.holiday.Holiday;
import com.example.apidemo.model.holiday.YearHoliday;
import com.example.apidemo.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("holidays")
public class HolidaysController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/{country}")
    public String holidays(@PathVariable String country) {
        System.out.println(
                "Recibio request!!!"
        );
        return holidayService.getHolidays(country);
    }

    @GetMapping("/v2/{country}")
    public Holiday holidaysV2(@PathVariable String country) {
        System.out.println(
                "Recibio request V2!!!"
        );
        return holidayService.getHolidaysV2(country);
    }

    @GetMapping("/v2/{country}/{year}")
    public YearHoliday holidaysV2(@PathVariable String country, @PathVariable Integer year) {
        System.out.println(
                "Recibio request V2!!!"
        );
        return holidayService.getHolidaysByYear(country, year);
    }

}
