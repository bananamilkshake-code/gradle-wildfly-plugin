# **wildfly-deploy** Gradle plugin #

### What is this repository for? ###

Gradle plugin to deploy web applications to Wildfly server.

### Plugin installation ###

First, you need to create gradle wrapper for plugin operation. Go to project's directory and run `gradle wrapper` (read [this](https://docs.gradle.org/current/userguide/gradle_wrapper.html) if you're not familiar with wrappers and want to know something about it).
After that run `./gradlew install` command in project's directory and plugin will be installed in local [maven](https://maven.apache.org/) repository.

### Use in project ###
To use plugin in your gradle project apply following code to projct's `gradle.build` file:


```
#!groovy

buildscript {
	repositories {
		mavenLocal()
	}
	dependencies {
		classpath group: 'me.bananamilkshake.gradle', name: 'wildfly-deploy', version: '0.1'
	}
}


apply plugin: 'wildfly-deploy'
```

There is some properties you can define for plugin.

```
#!groovy


wildflyDeploy {
	String libsDir // path to project build output dir (project's libDir by default)
	String projectName // name of packaged project
	String wildflyDir // wildfly directory ($JBOSS_HOME environment variable dy default)
}
```


### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact