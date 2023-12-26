package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherReportController.class)
public class WeatherReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    public void setUp() {
        when(weatherReportService.getWeatherReport(anyDouble(), anyDouble())).thenReturn(new WeatherReport());
    }

    @Test
    public void testGetWeatherReport() throws Exception {
        // Perform GET request
        mockMvc.perform(get("/v1/api/weather-report?latitude=37.8267&longitude=-122.4233"))
                .andExpect(status().isOk());

        // Verify that the service method was called with the correct parameters
        verify(weatherReportService).getWeatherReport(37.8267, -122.4233);
    }
}
