package de.seideman.dams.persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PersistenceAppListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PersistenceManager.getInstance().closeEntityManagerFactory();
	}

}
