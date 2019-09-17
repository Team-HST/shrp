package com.hst.shrp.configuration.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author dlgusrb0808@gmail.com
 */
public abstract class ApplicationInitializingHook {
	private InitializingContext initializingContext = new InitializingContext();
	Logger logger = LoggerFactory.getLogger(getClass());

	/***
	 * Returns whether hook execution is required.
	 * if return false, hook will not execute.
	 * @return Returns whether hook execution is required
	 */
	boolean isNecessaryExecuteHook() throws Exception {
		return false;
	}

	/***
	 * Implementation detail execute hook
	 * when <strong>isNecessaryExecuteHook</strong> return true, hook logic will execute.
	 * if <strong>isNecessaryExecuteHook</strong> return false, this method will never execute
	 */
	abstract void executeHook() throws Exception;

	/***
	 * Returns context
	 * @return context
	 */
	final InitializingContext getInitializingContext() {
		return initializingContext;
	}

	/***
	 * Get attribute from initializing context
	 * @param attributeName
	 * @return
	 */
	final Object getContextAttribute(String attributeName) {
		return getInitializingContext().getAttribute(attributeName);
	}

	/***
	 * set attribute to initializing context
	 * @param attributeName
	 * @return
	 */
	final void setContextAttribute(String attributeName, Object attributeValue) {
		getInitializingContext().setAttribute(attributeName, attributeValue);
	}

	public static class InitializingContext {
		private ThreadLocal<Map<String, Object>> threadLocalStore = new ThreadLocal<>();

		public void setAttribute(String attributeName, Object attributeValue) {
			Map<String, Object> currentThreadStore = getCurrentThreadStore();
			currentThreadStore.put(attributeName, attributeValue);
		}

		public Object getAttribute(String attributeName) {
			Map<String, Object> store = getCurrentThreadStore();
			if (!store.containsKey(attributeName)) {
				throw new IllegalStateException(String.format("Cannot found attribute with name %s", attributeName));
			}
			return store.get(attributeName);
		}

		private Map<String, Object> getCurrentThreadStore() {
			return Optional.ofNullable(threadLocalStore.get()).orElseGet(() -> {
				threadLocalStore.set(new HashMap<>());
				return threadLocalStore.get();
			});
		}
	}

}
