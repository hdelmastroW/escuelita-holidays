package com.example.apidemo.service;

import com.example.apidemo.model.holiday.Holiday;
import com.example.apidemo.model.holiday.YearHoliday;
import com.example.apidemo.persistence.entity.HolidayDescription;
import com.example.apidemo.persistence.repository.HolidayDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class HolidayService {

    // https://weholidays.s3.amazonaws.com/ar.json

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HolidayDescriptionRepository holidayDescriptionRepository;

    public String getHolidays(String country) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange("https://weholidays.s3.amazonaws.com/" + country + ".json", HttpMethod.GET, new HttpEntity(headers), String.class);
        return response.getBody();
    }

    public Holiday getHolidaysV2(String country) throws ParseException {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        messageConverters.add(jsonConverter);
        restTemplate.setMessageConverters(messageConverters);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");

        ResponseEntity<Holiday> response = restTemplate.exchange("https://weholidays.s3.amazonaws.com/" + country + ".json", HttpMethod.GET, new HttpEntity<Object>(headers), Holiday.class);

        HolidayDescription holidayDescription = new HolidayDescription();
        holidayDescription.setDescription(response.getBody().getYearHolidays().get(1).getHolidays().get(2).getDescription());
        String dateStr = response.getBody().getYearHolidays().get(1).getHolidays().get(2).getDate();
        holidayDescription.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr) );
        holidayDescriptionRepository.save(holidayDescription);
        return response.getBody();
    }

    public YearHoliday getHolidaysByYear(String country, Integer year) throws ParseException {
        Holiday holiday = getHolidaysV2(country);
        List<YearHoliday> yearHolidays = holiday.getYearHolidays();

        return yearHolidays
                .stream()
                .filter(yh -> yh.getYear().equals(year))
                .findFirst()
                .orElseThrow();
    }
}