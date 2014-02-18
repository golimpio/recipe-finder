package com.example.services;

import com.example.models.FridgeItem;
import com.example.models.Time;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/fridge")
@Produces(MediaType.APPLICATION_JSON)
public class FridgeService {

    @GET
    public Time get() {
        return new Time();
    }

    @PUT @Path("/add/{items}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addItems(@PathParam("items") String items) throws IOException {

        List<FridgeItem> fridgeItems = ItemsParser.parseFridgeItems(items);


    }

    public void uploadItems() {

    }

}

