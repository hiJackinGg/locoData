package com.controllers;

import com.db.DataStorage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/dataService")
public class DataService {

    @GET
    @Path("/parameters")
    @Produces("application/json")
    public String getParameters() {

        return DataStorage.getParameters();
    }

    @GET
    @Path("/values")
    @Produces("application/json")
    public String getData(@QueryParam("locomotiveId") int locomotiveId,
                          @QueryParam("parameterId")int parameterId,
                          @QueryParam("section") String section,
                          @QueryParam("valuesAmount") int valuesAmount,
                          @QueryParam("startTime") long startTime,
                          @QueryParam("endTime") long endTime)
    {


        return DataStorage.
                getValuesInTimeRange(locomotiveId, parameterId, section.equals("A"), startTime, endTime, valuesAmount).
                    toString();

    }

}
