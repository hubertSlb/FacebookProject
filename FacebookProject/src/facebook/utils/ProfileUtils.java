package facebook.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import facebook.entities.Facebook;
import facebook.entities.Post;
import facebook.interfaces.FacebookService;

public class ProfileUtils implements FacebookService {
	
	ProfileFile profileFile = new ProfileFile();

	@Override
	public Facebook findById(String id) throws NotFoundException {
		
		Facebook facebook = null;		
		Map<File, BufferedReader> map = profileFile.fileBufferProfile();
		Iterator<Entry<File, BufferedReader>> it = map.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<File, BufferedReader> profile = (Entry<File, BufferedReader>) it.next();
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(String.class, new ProfileIdDeserializer());
			Gson gson = gb.create();
			String profileId = gson.fromJson(profile.getValue(), String.class);
			if (!profileId.isEmpty() && profileId.equals(id)) {
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(profile.getKey()));
					gson = new GsonBuilder().create();
					facebook = gson.fromJson(br, Facebook.class);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (facebook == null) {
			throw new NotFoundException("No such profile with #ID: " + id);
		}
		
		return facebook;
	}

	@Override
	public Map<String, Long> findMostCommonWords() {
		
		Map<String, Long> result = new HashMap<String, Long>();
		StringBuilder sb = new StringBuilder();
		
		for (Post p : postsList())
			sb.append(p.getMessage()).append(" ");
		
		String str = sb.toString();
		str = str.replaceAll("(?s)(?<!\\S).(?!\\S)", "");
		str = str.replaceAll("([^a-zA-Z']+)|(\\s+)", " ");
		String[] words = str.toLowerCase().split(" ");
		
		result = Arrays.asList(words).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		result = SortUtils.mapSortByValue(result);
		
		return result;
	}

	@Override
	public Set<String> findPostIdsByKeyword(String word) {
		
		Set<String> postIds = new HashSet<String>();		
		for (Post p : postsList()) {
			if (p.getMessage().toLowerCase().contains(word.toLowerCase()))
				postIds.add(p.getId());
		}		
		return postIds;	
	}

	@Override
	public Set<Facebook> findAll() {
		
		Set<Facebook> profilesSet = new HashSet<Facebook>();
		SortedSet<Facebook> sortedProfiles = new TreeSet<Facebook>(
				Facebook.lastnameComparator.thenComparing(Facebook.firstnameComparator));
		
		for (BufferedReader br : profileFile.bufferedProfiles()) {
			Gson gson = new GsonBuilder().create();
			Facebook fb = gson.fromJson(br, Facebook.class);
			profilesSet.add(fb);
		}		
		sortedProfiles.addAll(profilesSet);
		
		return sortedProfiles;
	}
	
	private List<Post> postsList() {
		List<Post> result = new ArrayList<Post>();
		for (BufferedReader br : profileFile.bufferedProfiles()) {
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(Post[].class, new PostsDeserializer());
				Gson gson = gsonBuilder.create();
				Post[] posts = gson.fromJson(br, Post[].class);
				result.addAll(Arrays.asList(posts));
		}
		return result;
	}

}
