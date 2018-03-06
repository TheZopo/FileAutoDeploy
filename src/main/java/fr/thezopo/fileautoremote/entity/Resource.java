package fr.thezopo.fileautoremote.entity;

public class Resource {
	private String name;
	private String localPath;
	private String remotePath;
	private boolean active;
	private int index;
	
	public Resource(String name, String localPath, String remotePath, int index) {
		this.name = name;
		this.localPath = localPath;
		this.remotePath = remotePath;
		this.active = true;
		this.index = index;
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
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		return name + " - " + localPath + " - " + remotePath;
	}
}
