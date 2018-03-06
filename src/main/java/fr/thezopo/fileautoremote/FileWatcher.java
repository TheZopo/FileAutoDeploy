package fr.thezopo.fileautoremote;

import java.io.File;

import org.apache.log4j.helpers.FileWatchdog;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

import fr.thezopo.fileautoremote.entity.Entry;

public class FileWatcher extends FileWatchdog {
	private Entry entry;
	private int currentIndex;
	
	protected FileWatcher(Entry entry) {
		super(entry.getResource().getLocalPath().toString());
		this.entry = entry;
		this.currentIndex = 0;
		
		setDelay(2);
	}

	protected void doOnChange() {
		if(entry != null) {
			File file = new File(entry.getResource().getLocalPath());
			if(file.length() > 0) {
				if(currentIndex == entry.getResource().getIndex() - 1) {
					try {
						System.out.println("[" + entry.getName() + "] Resource " + entry.getResource().getName() + " changed...");
						System.out.print("[" + entry.getName() + "] Sending to remote " + entry.getRemote().getName() + "... [                     ]");
						
						Session session = entry.getRemote().getSession();
						session.connect();
						
						ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
						sftpChannel.connect();
						sftpChannel.put(entry.getResource().getLocalPath(), entry.getResource().getRemotePath(), new SftpProgressMonitor() {
							private long max;
							private long current;
							public void init(int arg0, String arg1, String arg2, long max) {
								this.max = max;
								this.current = 0;
							}
							
							public void end() {
								System.out.println();
							}
							
							public boolean count(long bytes) {
								current += bytes;
								int loading = (int) ((float)current / (float)max) * 20;
								
								System.out.print("\r[" + entry.getName() + "] Sending to remote " + entry.getRemote().getName() + "... [");
								for(int i = 0; i < loading; i++) System.out.print("=");
								System.out.print(">");
								for(int i = 0; i < 20 - loading; i++) System.out.print(" ");
								System.out.print("]");
								
								return true;
							}
						});
						sftpChannel.disconnect();
						
						System.out.println("[" + entry.getName() + "] Executing commands...");
						ChannelExec commandChannel = null;
						for(String command : entry.getCommands()) {
							commandChannel = (ChannelExec) session.openChannel("exec");
							commandChannel.setCommand(command);
							commandChannel.setPty(false);
							commandChannel.connect();
							commandChannel.disconnect();
						}
						System.out.println("[" + entry.getName() + "] Deployment complete!");
						
						session.disconnect();
					} catch (JSchException | SftpException e) {
						e.printStackTrace();
						
					}
					currentIndex = 0;
					
					System.out.println("Listening...");
				} else {
					currentIndex++;
				}
			}
		}
	}
}
