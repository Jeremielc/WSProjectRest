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

import fr.ensicaen.si.dao.client.ClientDao;
import fr.ensicaen.si.dao.client.DbClientDao;
import fr.ensicaen.si.model.Client;

@Path("/clients")
public class ClientsResources {
	@Context UriInfo uriInfo;
	@Context Request request;

	ClientDao cDao;

	public ClientsResources() {
		cDao = ClientDao.getInstance();
		cDao.setDelegate(new DbClientDao());
	}

	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON/* + ";charset-utf-8"*/})
	public List<Client> getClientList() {
		return cDao.getClients();
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN/* + ";charset-utf-8"*/)
	public String getCount() {
		return String.valueOf(cDao.countClient());
	}

	@GET
	@Path("id/{clientid}")
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON/* + ";charset-utf-8"*/})
	public Client getClientById(@PathParam("clientid") int id) {
		return cDao.getById(id);
	}
	
	@GET
	@Path("{name}")
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON/* + ";charset-utf-8"*/})
	public List<Client> getClientByName(@PathParam("name") String name) {
		return cDao.getByName(name);
	}
	
	@GET
	@Path("{surname}/{name}")
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON/* + ";charset-utf-8"*/})
	public List<Client> getClientByFullname(@PathParam("name") String name, @PathParam("surname") String surname) {
		return cDao.getByFullname(surname, name);
	}
}
