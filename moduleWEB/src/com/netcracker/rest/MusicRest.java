package com.netcracker.rest;

import com.netcracker.entity.MusicTypeEntity;
import com.netcracker.facade.local_int.MusicType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Ubuntu on 12.05.2015.
 */
@Stateless
@Path("music")
public class MusicRest {

    @EJB
    MusicType musicType;

    @POST
    @Path("type")
    public Response getMusicType() {
        List<MusicTypeEntity> musicTypeEntities = musicType.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"musicType\":[");
        for (MusicTypeEntity musicTypeEntity : musicTypeEntities) {
            sb.append("{\"id\":\"")
                    .append(musicTypeEntity.getId())
                    .append("\",\"name\":\"")
                    .append(musicTypeEntity.getName())
                    .append("\" },");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]}");
        if (!musicTypeEntities.isEmpty()) {
            return Response.status(200).entity(sb.toString()).build();
        } else {
            return Response.status(404).entity("Bad response.").build();
        }
    }

}
