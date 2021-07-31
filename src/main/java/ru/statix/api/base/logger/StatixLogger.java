package ru.statix.api.base.logger;

import java.util.logging.*;

public class StatixLogger extends Logger {

	/**
	 * Protected method to construct a logger for a named subsystem.
	 * <p>
	 * The logger will be initially configured with a null Level
	 * and with useParentHandlers set to true.
	 */
	public StatixLogger() {
		super("StatixCode", null);

		setLevel(Level.ALL);
	}

}

