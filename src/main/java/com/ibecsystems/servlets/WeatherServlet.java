package com.ibecsystems.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ibecsystems.model.Weather;
import com.ibecsystems.service.WeatherService;
import com.ibecsystems.service.impl.WeatherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WeatherServlet extends HttpServlet {

    private final static Logger LOGGER =
            Logger.getLogger(com.ibecsystems.servlets.TimeZoneServlet.class.getName());
    private WeatherService weatherService = new WeatherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityCode = req.getPathInfo().substring(1);
        ObjectMapper mapper = new ObjectMapper();
        Weather weather;

        try {
            weather = weatherService.createWeather(cityCode);
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), weather);

            resp.setStatus(200);
            LOGGER.info("request received");
        } catch (InvalidFormatException e) {
            resp.sendError(400, "The code can include only numbers");
            LOGGER.log(Level.SEVERE, "Exception occur", e);
        } catch (NumberFormatException e) {
            resp.sendError(400, "The server cannot find the requested city code");
            LOGGER.log(Level.SEVERE, "Exception occur", e);
        }

    }
}
