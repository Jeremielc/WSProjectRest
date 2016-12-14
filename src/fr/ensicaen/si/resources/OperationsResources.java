package fr.ensicaen.si.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import fr.ensicaen.si.dao.operation.DbOperationDao;
import fr.ensicaen.si.dao.operation.OperationDao;
import fr.ensicaen.si.model.Operation;

@Path("/operations")
public class OperationsResources {
	@Context UriInfo uriInfo; 
	@Context Request request;
	
	OperationDao oDao;
	
	public OperationsResources() {
		oDao = OperationDao.getInstance();
		oDao.setDelegate(new DbOperationDao());
	}
	
	@GET
	@Path("id/{clientid}")
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON/* + ";charset-utf-8"*/})
	public List<Operation> getOperationById(@PathParam("clientid") int id) {
		return oDao.getById(id);
	}
	
	@GET
	@Path("{name}")
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON/* + ";charset-utf-8"*/})
	public List<Operation> getOpeartionByName(@PathParam("name") String name) {
		return oDao.getByName(name).getOperationList();
	}
	
	@GET
	@Path("{surname}/{name}")
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON/* + ";charset-utf-8"*/})
	public List<Operation> getOperationByFullname(@PathParam("surname") String surname, @PathParam("name") String name) {
		return oDao.getByFullname(surname, name).getOperationList();
	}
}
