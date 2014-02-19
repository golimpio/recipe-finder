package com.example.services;

import com.example.models.Recipe;
import com.example.models.RecipeResponse;
import com.example.repository.RecipesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status;

@Path("/recipes")
public class RecipesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipesService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recipe> get() {
        return RecipesRepository.instance().get();
    }

    @POST @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public RecipeResponse add(List<Recipe> recipes) {
        LOGGER.info("Adding recipes: [{}]", recipes);

        try {
            RecipesRepository.instance().update(recipes);
        } catch(IllegalArgumentException e) {
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
        }

        return ResponseHelper.getRecipe();
    }

    public void uploadItems() {

    }

}

