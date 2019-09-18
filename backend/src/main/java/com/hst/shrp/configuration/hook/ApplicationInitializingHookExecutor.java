package com.hst.shrp.configuration.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ApplicationInitializingHookExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializingHookExecutor.class);

    @Autowired
    List<ApplicationInitializingHook> applicationInitializingHooks;

    @PostConstruct
    public void executeHooks() {
        for (ApplicationInitializingHook hook : applicationInitializingHooks) {
            executeHook(hook);
        }
    }

    private void executeHook(ApplicationInitializingHook hook) {
        logger.info("Execute Hook {}", hook.getClass());

        try {
            if (hook.isNecessaryExecuteHook()) {
                logger.info("Need to execute hook");
                hook.executeHook();
            } else {
                logger.info("No need to execute hook");
            }
        } catch (Exception e) {
            reportAndShutdownApp(e);
        }
    }

    private void reportAndShutdownApp(Exception e) {
        logger.error(e.getMessage(), e);
        System.exit(-1);
    }
}
