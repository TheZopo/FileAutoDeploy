package fr.thezopo.fileautoremote.entity;

public class Resource {
	private String name;
	private String localPath;
	private String remotePath;
	private boolean active;
	
	public Resource(String name, String localPath, String remotePath) {
		this.name = name;
		this.localPath = localPath;
		this.remotePath = remotePath;
		this.active = true;
	}

	public Resource() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public boolean isActive() {
		return active;
	}

	public void active() {
		active = true;
	}
	
	public void desactive() {
		active = false;
	}
	
	public String toString() {
		return name + " - " + localPath + " - " + remotePath;
	}
}
