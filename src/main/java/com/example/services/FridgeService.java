package com.example.services;

import com.example.models.FridgeItem;
import com.example.repository.FridgeRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/fridge")
@Produces(MediaType.APPLICATION_JSON)
public class FridgeService {

    @GET
    public List<FridgeItem> get() {
        return FridgeRepository.instance().get();
    }

    @PUT @Path("/add/{items}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addItems(@PathParam("items") String items) throws IOException {
        List<FridgeItem> fridgeItems = ItemsParser.parseFridgeItems(items);
        FridgeRepository.instance().update(fridgeItems);
    }

    public void uploadItems() {

    }

}

