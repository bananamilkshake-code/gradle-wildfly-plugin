package me.bananamilkshake.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class WildflyDeployPlugin implements Plugin<Project> {

	private final Logger log = LoggerFactory.getLogger(this.getClass())

	void apply(Project project) {
		project.extensions.create('wildflyDeploy', WildflyDeployPluginExtension)
		
		project.task('deploy', dependsOn: 'assemble') << {
			if (project.wildflyDeploy.libsDir == null) {
				project.wildflyDeploy.libsDir= project.libsDir
			}
			log.debug("Project build output directory: ${project.wildflyDeploy.wildflyDir}")

			if (project.wildflyDeploy.projectName == null) {
				project.wildflyDeploy.projectName = project.name
			}
			log.debug("Project file: ${project.wildflyDeploy.projectName}")

			if (project.wildflyDeploy.wildflyDir == null) {
				project.wildflyDeploy.wildflyDir = System.getenv('JBOSS_HOME')
			}
			log.debug("Wildfly path: ${project.wildflyDeploy.wildflyDir}")
			
			String wildflyDeploymentDir = "${project.wildflyDeploy.wildflyDir}/standalone/deployments/"
			
			log.info("Copying '${project.wildflyDeploy.libsDir}/${project.wildflyDeploy.projectName}' to wildfly ('${wildflyDeploymentDir}')")
			
			project.copy {
				into wildflyDeploymentDir
				from(project.wildflyDeploy.libsDir) {
					include "**/${project.wildflyDeploy.projectName}"
				}
			}
		}
	}
}

class WildflyDeployPluginExtension {
	String libsDir
	String projectName
	String wildflyDir
} 
