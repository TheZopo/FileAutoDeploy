FileAutoDeploy
===
Application deploying files on a remote server and executing commands

## Use
Launch the application with `java -jar FileAutoDeploy.jar`
Type `exit` in the console for exit

## Configuration
Configuration is located in the same directory of the app and named `fileAutoDeploy.config`

Configuration example:
```
- !fr.thezopo.fileautoremote.entity.Entry
   name: MyEntry
   commands: 
   - touch /home/hello.world
   - echo Hello World
   remote: 
      name: MyRemote
      host: 127.0.0.1
      keyPath: D:/SSH/thezopo.openssh
      passPhrase: RosesAreRed
      port: 22
      username: root
   resource: 
      name: MyProject
      localPath: D:/Code/JAVA/MyProject/target/myproject-1.0.jar
      remotePath: /home/myservice/myproject-1.0.jar
```