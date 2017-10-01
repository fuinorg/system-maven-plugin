# system-maven-plugin
This [Maven](https://maven.apache.org/) plugin provides UnixSystem values like "uid" or "gid" as project properties.

[![Build Status](https://fuin-org.ci.cloudbees.com/job/system-maven-plugin/badge/icon)](https://fuin-org.ci.cloudbees.com/job/system-maven-plugin/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.fuin.smp/system-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.fuin.smp/system-maven-plugin/)
[![LGPLv3 License](http://img.shields.io/badge/license-LGPLv3-blue.svg)](https://www.gnu.org/licenses/lgpl.html)
[![Java Development Kit 1.8](https://img.shields.io/badge/JDK-1.8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

<a href="https://fuin-org.ci.cloudbees.com/job/system-maven-plugin"><img src="http://www.fuin.org/images/Button-Built-on-CB-1.png" width="213" height="72" border="0" alt="Built on CloudBees"/></a>

### Getting started
Just add the plugin to your Maven POM:
```xml
<plugin>
	<groupId>org.fuin.smp</groupId>
	<artifactId>system-maven-plugin</artifactId>
	<version>0.1.0-SNAPSHOT</version>
	<configuration>
	    <!-- The default prefix is 'org.fuin.smp.' -->
		<generic-prefix>g.</generic-prefix>
	    <!-- The default prefix is 'org.fuin.smp.unix.' -->
		<unix-prefix>u.</unix-prefix>
	</configuration>
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

**Generic**

| Property | Description |
|----------|-------------|
| org.fuin.smp.uid | UID for the current Unix user or printable primary group SID for the current NT user (String) |
| org.fuin.smp.gid | GID for the current Unix user or printable SID for the current NT user (String) |
| org.fuin.smp.username | Username for the current user (String) |


**UnixSystem**

| Property | Description |
|----------|-------------|
| org.fuin.smp.unix.gid | GID for the current Unix user (long) |
| org.fuin.smp.unix.groups | Supplementary groups for the current Unix user (long[]) |
| org.fuin.smp.unix.uid | UID for the current Unix user (long) |
| org.fuin.smp.unix.username | Username for the current Unix user (String) |


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
