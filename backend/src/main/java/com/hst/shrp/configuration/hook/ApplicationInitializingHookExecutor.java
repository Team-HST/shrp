package com.hst.shrp.configuration.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ApplicationInitializingHookExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializingHookExecutor.class);

    private final List<ApplicationInitializingHook> applicationInitializingHooks;

    @Autowired(required = false)
    public ApplicationInitializingHookExecutor(List<ApplicationInitializingHook> applicationInitializingHooks) {
        this.applicationInitializingHooks = applicationInitializingHooks;
    }

    @PostConstruct
    public void executeHooks() {
        if (!CollectionUtils.isEmpty(applicationInitializingHooks)) {
            logger.info("{} executable hook registered", applicationInitializingHooks.size());

            for (ApplicationInitializingHook hook : applicationInitializingHooks) {
                executeHook(hook);
            }
        } else {
            logger.info("No executable hook registered.");
        }
    }

    // template method for execute hook
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
            if (logger.isTraceEnabled()) {
                e.printStackTrace();
            }
            reportAndShutdownApp(e);
        }
    }

    private void reportAndShutdownApp(Exception e) {
        logger.error(e.getMessage(), e);
        System.exit(-1);
    }
}
