# system-maven-plugin
This [Maven](https://maven.apache.org/) plugin provides UnixSystem values like "uid" or "gid" as project properties.

[![Java Maven Build](https://github.com/fuinorg/system-maven-plugin/actions/workflows/maven.yml/badge.svg)](https://github.com/fuinorg/system-maven-plugin/actions/workflows/maven.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.fuin.smp/system-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.fuin.smp/system-maven-plugin/)
[![LGPLv3 License](http://img.shields.io/badge/license-LGPLv3-blue.svg)](https://www.gnu.org/licenses/lgpl.html)
[![Java Development Kit 11](https://img.shields.io/badge/JDK-11-green.svg)](https://openjdk.java.net/projects/jdk/11/)

## Versions
- 0.2.x (or later) = **Java 11**
- 0.1.0 = **Java 8**


### Getting started
Just add the plugin to your Maven POM:
```xml
<plugin>
    <groupId>org.fuin.smp</groupId>
    <artifactId>system-maven-plugin</artifactId>
    <version>0.1.0</version>
    <!-- You can optionally change the prefixes
    <configuration>
        <generic-prefix>org.fuin.smp.</generic-prefix>
        <unix-prefix>org.fuin.smp.unix.</unix-prefix>
    </configuration>
    -->
    <executions>
        <execution>
            <phase>initialize</phase>
            <goals>
                <goal>read-system-information</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
This will make all properties from [UnixSystem](https://docs.oracle.com/javase/8/docs/jre/api/security/jaas/spec/com/sun/security/auth/module/UnixSystem.html) available as project properties.

Here is a [Docker Maven Plugin](https://github.com/fabric8io/docker-maven-plugin) example on how to use the properties:
```xml
<plugin>
    <groupId>io.fabric8</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>0.22.1</version>
    <configuration>
        <images>
            <image>
                <name>your/cool-image</name>
                <run>
                    <volumes>
                        <bind>
                            <volume>${project.build.directory}:/usr/src/result</volume>
                        </bind>
                    </volumes>
                    <user>${org.fuin.smp.uid}:${org.fuin.smp.gid}</user>
                </run>
            </image>
        </images>
    </configuration>
</plugin>
```
This starts the image with the local user's UID and GID and files written into '${project.build.directory}' will have exactly that owner and group.

### Available Properties

**Generic**

| Property | Description |
|:---------|:------------|
| org.fuin.smp.uid | UID for the current user (String) |
| org.fuin.smp.gid | GID for the current user (String) |
| org.fuin.smp.username | Username for the current user (String) |


**UnixSystem**

| Property | Description |
|:---------|:------------|
| org.fuin.smp.unix.gid | GID for the current Unix user (long) |
| org.fuin.smp.unix.groups | Supplementary groups for the current Unix user (long[]) |
| org.fuin.smp.unix.uid | UID for the current Unix user (long) |
| org.fuin.smp.unix.username | Username for the current Unix user (String) |



### Limitations

Currently only Unix like systems are supported. It should also be possible to include [NTSystem](https://docs.oracle.com/javase/8/docs/jre/api/security/jaas/spec/com/sun/security/auth/module/NTSystem.html) 
but somehow the class was missing in my local Java 8 JRE and I hadn't enough time to investigate this. Any help welcome.

### Snapshots

Snapshots can be found on the [OSS Sonatype Snapshots Repository](http://oss.sonatype.org/content/repositories/snapshots/org/fuin "Snapshot Repository"). 

Add the following to your .m2/settings.xml to enable snapshots in your Maven build:

```xml
<pluginRepository>
    <id>sonatype.oss.snapshots</id>
    <name>Sonatype OSS Snapshot Repository</name>
    <url>http://oss.sonatype.org/content/repositories/snapshots</url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</pluginRepository>
```
