package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    public void testGetWeatherReport() {
        // Mocking the response from the external API
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main mockMain = new WeatherApiResponse.Main();
        mockMain.setTemperature(25.5); // Set a sample temperature
        mockMain.setHumidity(60.0);    // Set a sample humidity
        mockApiResponse.setMain(mockMain);

        // Mocking the RestTemplate behavior
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(mockApiResponse);

        // Perform the test
        WeatherReport weatherReport = weatherReportService.getWeatherReport(37.8267, -122.4233);

        // Verify the result
        assertEquals(25.5, weatherReport.getTemperature(), 0.01);
        assertEquals(60.0, weatherReport.getHumidity(), 0.01);
    }
}
