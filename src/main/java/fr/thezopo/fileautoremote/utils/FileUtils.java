package fr.thezopo.fileautoremote.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import fr.thezopo.fileautoremote.FileAutoDeploy;

public class FileUtils {
	public static String getJarPath() {
		String url = null;
		try {
			url = FileAutoDeploy.class.getProtectionDomain().getCodeSource().getLocation().toURI().resolve(".").getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public static boolean fileIsEmpty(File file) {
		String line = "notEmpty";
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			line = br.readLine();

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return line == null;
	}
}
