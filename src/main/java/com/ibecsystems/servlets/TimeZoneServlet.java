package com.ibecsystems.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ibecsystems.model.TimeZone;
import com.ibecsystems.service.TimeZoneService;
import com.ibecsystems.service.impl.TimeZoneServiceImpl;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeZoneServlet extends HttpServlet {

    private final static Logger LOGGER =
            Logger.getLogger(com.ibecsystems.servlets.TimeZoneServlet.class.getName());
    private TimeZoneService timeZoneService = new TimeZoneServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        TimeZone timeZone;

        try {
            timeZone = mapper.readValue(req.getReader(), TimeZone.class);
            timeZoneService.getTimeZone(timeZone);

            resp.setContentType("application/json");
            mapper.writeValue(resp.getOutputStream(), timeZone);
            resp.setStatus(200);
            LOGGER.info("request received");
        } catch (InvalidFormatException e) {
            resp.sendError(400, "The code can include only numbers");
            LOGGER.log(Level.SEVERE, "Exception occur", e);
        } catch (JSONException e) {
            resp.sendError(404, "The requested zipCode does not exists");
            LOGGER.log(Level.SEVERE, "Exception occur", e);
        }
    }
}
