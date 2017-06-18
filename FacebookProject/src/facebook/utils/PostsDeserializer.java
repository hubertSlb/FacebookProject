package facebook.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import facebook.entities.Post;

public class PostsDeserializer implements JsonDeserializer<Post[]> {

	@Override
	public Post[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonElement jsonEl = json.getAsJsonObject().get("posts").getAsJsonArray();
		
		return new Gson().fromJson(jsonEl, Post[].class);
	}

}
