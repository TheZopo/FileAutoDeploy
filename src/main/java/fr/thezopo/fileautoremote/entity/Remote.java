package fr.thezopo.fileautoremote.entity;

import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Remote {
	private String name;
	private String username;
	private String host;
	private int port;
	private String keyPath;
	private String passPhrase;
	private Session session;
	
	public Remote(String name, String username, String host, int port, String keyPath, String passPhrase) {
		this.name = name;
		this.username = username;
		this.host = host;
		this.port = port;
		this.keyPath = keyPath;
		this.passPhrase = passPhrase;
	}
	
	public void initialize() {
	    JSch jsch = new JSch();
	    session = null;
	    try {
	        jsch.addIdentity(keyPath, passPhrase);
	        session = jsch.getSession(username, host, port);
	        session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
	        java.util.Properties config = new Properties(); 
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
	    } catch (JSchException e) {
	        throw new RuntimeException("Failed to create Jsch Session object.", e);
	    }
	}
	
	public Remote() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getPassPhrase() {
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String toString() {
		return name + " - " + username + "@" + host + ":" + port;
	}
}
