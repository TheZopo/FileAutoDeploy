package fr.thezopo.fileautoremote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import fr.thezopo.fileautoremote.entity.Entry;

public class FileAutoDeploy {
	public static void main(String args[]) throws IOException {
		HashMap<String, FileWatcher> watchers = new HashMap<>();
		
		System.out.println("  _____ _ _         _         _        ____             _");
		System.out.println(" |  ___(_) | ___   / \\  _   _| |_ ___ |  _ \\  ___ _ __ | | ___  _   _");
		System.out.println(" | |_  | | |/ _ \\ / _ \\| | | | __/ _ \\| | | |/ _ \\ '_ \\| |/ _ \\| | | |");
		System.out.println(" |  _| | | |  __// ___ \\ |_| | || (_) | |_| |  __/ |_) | | (_) | |_| |");
		System.out.println(" |_|   |_|_|\\___/_/   \\_\\__,_|\\__\\___/|____/ \\___| .__/|_|\\___/ \\__, |");
		System.out.println("                                                 |_|            |___/");
		System.out.println("By Bastien \"TheZopo\" MARSAUD");
		System.out.println("=======================================================================");
		
        for(Entry entry : Entry.load()) {
        	System.out.println(entry);
        	
        	FileWatcher watcher = new FileWatcher(entry);
        	watchers.put(entry.getName(), watcher);
        	watcher.start();
        }
        
        System.out.println("=======================================================================");
        System.out.println("Listening...");
        
        Scanner scanner = new Scanner(System.in);
        while(!scanner.nextLine().equals("exit"));
        scanner.close();
        
        for(FileWatcher watcher : watchers.values()) watcher.interrupt();
        System.out.println("Goodbye!");
	}
}
