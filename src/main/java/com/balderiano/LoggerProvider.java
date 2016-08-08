package com.balderiano;

import java.util.logging.Logger;

public class LoggerProvider {
	public Logger get(String loggerName) {
		return Logger.getLogger(loggerName);
	}
}
