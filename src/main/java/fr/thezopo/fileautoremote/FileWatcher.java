package fr.thezopo.fileautoremote;

import org.apache.log4j.helpers.FileWatchdog;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import fr.thezopo.fileautoremote.entity.Entry;

public class FileWatcher extends FileWatchdog {
	private Entry entry;
	
	protected FileWatcher(Entry entry) {
		super(entry.getResource().getLocalPath().toString());
		this.entry = entry;
		
		setDelay(2);
	}

	protected void doOnChange() {
		if(entry != null) {
			try {
				System.out.println("[" + entry.getName() + "] Resource " + entry.getResource().getName() + " changed...");
				System.out.println("[" + entry.getName() + "] Sending to remote " + entry.getRemote().getName() + "...");

				entry.getRemote().initialize();
				entry.getRemote().getSession().connect();
				
				ChannelSftp sftpChannel = (ChannelSftp) entry.getRemote().getSession().openChannel("sftp");
				sftpChannel.connect();
				sftpChannel.put(entry.getResource().getLocalPath(), entry.getResource().getRemotePath());
				sftpChannel.disconnect();

				System.out.println("[" + entry.getName() + "] Executing commands...");
				
				ChannelExec commandChannel = null;
				for(String command : entry.getCommands()) {
					commandChannel = (ChannelExec) entry.getRemote().getSession().openChannel("exec");
					commandChannel.setCommand(command);
					commandChannel.setPty(false);
					commandChannel.connect();
					commandChannel.disconnect();
				}
			    System.out.println("[" + entry.getName() + "] Deployment complete!");
			    
				entry.getRemote().getSession().disconnect();
			} catch (JSchException | SftpException e) {
				e.printStackTrace();
			}
		}
	}
}
