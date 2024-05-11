package dat152.ajax.services;

import java.util.Calendar;

import jakarta.ejb.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import dat152.ajax.response.ServerResponse;
import dat152.ajax.response.ServertimeResponse;

@Singleton
@Path("/ajax7")
public class RepeatedAjaxDemo {

	@Path("/servertime")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ServerResponse getStudent() {
		Calendar c = Calendar.getInstance();
		ServertimeResponse response = new ServertimeResponse();

		response.setResponseStatus(true);
		response.setServertime(c.getTimeInMillis());
		return response;
	}

}
