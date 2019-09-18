package com.hst.shrp.configuration.hook;

import com.hst.shrp.configuration.annotation.InitializingHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author dlgusrb0808@gmail.com
 */
@InitializingHook
@Order(1)
public class DeployDatabaseResourceCopyHook extends ApplicationInitializingHook {

	private Logger logger = LoggerFactory.getLogger(DeployDatabaseResourceCopyHook.class);

	@Value("${shrp.db.force-overwrite}")
	private boolean forceOverwrite;

	@Value("${shrp.db.source-location}")
	private String sourceLocation;

	@Value("${shrp.db.deploy-location}")
	private String deployLocation;

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	protected boolean isNecessaryExecuteHook() throws Exception {
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

	@Override
	protected void executeHook() throws Exception {
		File sourceFile = getSourceFile();
		File destinationFile = getDestinationFile();
		FileCopyUtils.copy(sourceFile, destinationFile);
		logger.info("Copy database resource {} to {}", sourceFile, destinationFile);
	}

	// prepare sourceFile
	private File getSourceFile() throws IOException {
		Resource resource = resourceLoader.getResource(sourceLocation);
		if (resource == null || !resource.exists()) {
			logger.error("Source database file {} is not exist.", sourceLocation);
			throw new IOException(String.format("Source database file not exist. %s", sourceLocation));
		} else {
			logger.info("Detect database resource file. {}", resource.getURL());
			return resource.getFile();
		}
	}

	// prepare destinationFile
	private File getDestinationFile() throws IOException {
		File destinationFile = new File(deployLocation);
		File copyDestinationDirectory = destinationFile.getParentFile();
		if (!copyDestinationDirectory.exists()) {
			if (!copyDestinationDirectory.mkdirs()) {
				logger.error("Can not create directory on {}", copyDestinationDirectory.getAbsolutePath());
				throw new IOException(String.format("Can not create directory on %s", copyDestinationDirectory.getAbsolutePath()));
			}
		}
		return destinationFile;
	}

}
