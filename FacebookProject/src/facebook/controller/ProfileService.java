package facebook.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import facebook.entities.Facebook;
import facebook.utils.ProfileFile;
import facebook.utils.ProfileUtils;

@Path("/")
public class ProfileService {
	

	ProfileUtils profileUtils = new ProfileUtils();
	ProfileFile profileFile = new ProfileFile();
	
	@GET
	@Path("/profile/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("id") String id) {
		try {
		Facebook profile = profileUtils.findById(id);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(profile);
		return Response.status(200).entity(json).build();
		} catch (NotFoundException e) {
			e.printStackTrace();
			return Response.status(404).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/post/commonwords")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommonWords() {
		Map<String, Long> result = new HashMap<>();
		result = profileUtils.findMostCommonWords();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(result);
		return Response.status(200).entity(json).build();
	}
	
	@GET
	@Path("/post/word/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostIds(@PathParam("word") String word){
		Set<String> result = new HashSet<>();
		result = profileUtils.findPostIdsByKeyword(word);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(result);
		return Response.status(200).entity(json).build();
	}
	
	@GET
	@Path("/profile/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProfiles() {
		Set<Facebook> result = new HashSet<>();
		result = profileUtils.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(result);
		return Response.status(200).entity(json).build();
	}
	
	@GET
	@Path("/filetest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFiles() {
		List<File> list = new ArrayList<>();
		list = profileFile.getFilesProfile();
		String json = new Gson().toJson(list);
		
		return Response.status(200).entity(json).build();
	}
}
