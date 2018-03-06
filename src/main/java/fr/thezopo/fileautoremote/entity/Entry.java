package fr.thezopo.fileautoremote.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import fr.thezopo.fileautoremote.utils.FileUtils;

public class Entry {
	private String name;
	private Remote remote;
	private Resource resource;
	private ArrayList<String> commands;
	
	public Entry(String name, Remote remote, Resource resource, ArrayList<String> commands) {
		this.name = name;
		this.remote = remote;
		this.resource = resource;
		this.commands = commands;
	}
	
	public Entry() {
		
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Entry> load() {
		File file = new File(FileUtils.getJarPath(), "fileAutoDeploy.config");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		ArrayList<Entry> entries = new ArrayList<>();

		if(!FileUtils.fileIsEmpty(file)) {
			try {
				YamlReader reader = new YamlReader(new FileReader(file));
				entries = (ArrayList<Entry>) reader.read(ArrayList.class);
			} catch (FileNotFoundException | YamlException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Configuration is empty");
		}
		
		return entries;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Remote getRemote() {
		return remote;
	}

	public void setRemote(Remote remote) {
		this.remote = remote;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}
	
	public String toString() {
		return "=== " + name + " ===\nResource: " + resource + "\nRemote:\t" + remote;
	}
}
