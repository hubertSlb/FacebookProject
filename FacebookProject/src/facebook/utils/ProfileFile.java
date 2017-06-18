package facebook.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileFile {
	
	private ClassLoader classLoader = this.getClass().getClassLoader();
	private URL url = classLoader.getResource("profiles");
	
	public List<File> getFilesProfile() {
		File dir = null;
		try {
			dir = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return Arrays.asList(dir.listFiles());
	}
	
	public List<BufferedReader> bufferedProfiles() {
		List<BufferedReader> result = new ArrayList<BufferedReader>();
		for (File f : getFilesProfile()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				result.add(br);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Map<File, BufferedReader> fileBufferProfile() {
		Map<File, BufferedReader> result = new HashMap<File, BufferedReader>();
		for (File f : getFilesProfile()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				result.put(f, br);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
