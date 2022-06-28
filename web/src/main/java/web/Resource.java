package web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dto.UtilityDto;
import ejb.Service;

@Path("/")
@RequestScoped
public class Resource {
	@Inject Service service;
	
	@GET
	@Path("/utility")
	@Produces("application/json")
	public UtilityDto getUtilityDto() {
		return service.compute();
	}
	
	@GET
	@Path("/web-local")
	@Produces("application/json")
	public WebDto getWebDto() {
		return new WebDto();
	}
}
