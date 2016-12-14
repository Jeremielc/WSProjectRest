package fr.ensicaen.si.init;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import fr.ensicaen.si.db.DbManagement;
import fr.ensicaen.si.db.MySqlDbManagement;

public class ServletContextClass implements ServletContextListener {
	@Context UriInfo uriInfo; 
	@Context Request request;
	
	DbManagement dbMan;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			dbMan.disconnection();
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		dbMan = DbManagement.getInstance();
		dbMan.setDelegate(new MySqlDbManagement());
	}
	

}
