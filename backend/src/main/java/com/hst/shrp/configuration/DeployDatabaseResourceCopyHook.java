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
			if (initialize()) {
				copyDatabaseResourceFile();
			}
		} catch (Exception e) {
			logger.error("Can not copy shrp database file.", e);
			System.exit(-1);
		}
	}

	private boolean initialize() throws IOException {
		destinationFile = new File(deployLocation);
		if (destinationFile.exists() && destinationFile.isFile()) {
			logger.info(String.format("Already exist deployed database file. %s", destinationFile.getAbsolutePath()));
			return false;
		} else {
			Resource resource = resourceLoader.getResource(sourceLocation);
			if (resource == null || !resource.exists()) {
				throw new IOException(String.format("Source database file not exist. %s", sourceLocation));
			} else {
				sourceFile = resource.getFile();
			}
		}
		logger.info("Detect database resource file. {}", sourceFile);
		return true;
	}


	private void copyDatabaseResourceFile() throws IOException {
		if (destinationFile.isFile()) {
			destinationFile.getParentFile().mkdirs();
		}
		FileCopyUtils.copy(sourceFile, destinationFile);
		logger.info("Copy database resource {} to {}", sourceFile, destinationFile);
	}

}
