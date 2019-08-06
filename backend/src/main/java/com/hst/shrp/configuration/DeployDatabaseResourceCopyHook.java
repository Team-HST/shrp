package com.hst.shrp.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
@Order(0)
public class DeployDatabaseResourceCopyHook {

	private Logger logger = LoggerFactory.getLogger(DeployDatabaseResourceCopyHook.class);

	@Value("${shrp.db.force-overwrite}")
	private boolean forceOverwrite;

	@Value("${shrp.db.source-location}")
	private String sourceLocation;

	@Value("${shrp.db.deploy-location}")
	private String deployLocation;

	private File sourceFile;
	private File destinationFile;

	@Autowired
	private ResourceLoader resourceLoader;

	@PostConstruct
	public void onStartUpHook() {
		try {
			if (isRequireCopy()) {
				initialize();
				copyDatabaseResourceFile();
			}
		} catch (Exception e) {
			logger.error("Can not copy shrp database file.", e);
			System.exit(-1);
		}
	}

	// check database copy require
	private boolean isRequireCopy() {
		if (forceOverwrite) {
			logger.info("Force overwrite switch ON.");
			return true;
		} else {
			File destinationFile = new File(deployLocation);
			if (destinationFile.exists() && destinationFile.isFile()) {
				logger.info("Already exist deployed database file. {}", destinationFile.getAbsolutePath());
				return false;
			}
			return true;
		}
	}

	// initialize src, dest
	private void initialize() throws IOException {
		destinationFile = new File(deployLocation);
		Resource resource = resourceLoader.getResource(sourceLocation);
		if (resource == null || !resource.exists()) {
			logger.error("Source database file {} is not exist.", sourceLocation);
			throw new IOException(String.format("Source database file not exist. %s", sourceLocation));
		} else {
			sourceFile = resource.getFile();
			logger.info("Detect database resource file. {}", sourceFile);
		}
	}

	// process copy
	private void copyDatabaseResourceFile() throws IOException {
		if (destinationFile.getParentFile().mkdirs()) {
			FileCopyUtils.copy(sourceFile, destinationFile);
			logger.info("Copy database resource {} to {}", sourceFile, destinationFile);
		} else {
			logger.error("Can not create directory on {}", destinationFile.getParentFile().getAbsolutePath());
			throw new IOException();
		}
	}

}
