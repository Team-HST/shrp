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
            logger.info("Execute Hook {}", hook.getClass());
            execute(hook);
        }
    }

    private void execute(ApplicationInitializingHook hook) {
        try {
            if (hook.isNecessaryExecuteHook()) {
                hook.executeHook();
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
