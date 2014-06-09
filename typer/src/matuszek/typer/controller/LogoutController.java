package matuszek.typer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/logout")
public class LogoutController {

	@POST
	public void logout(@Context HttpServletRequest req) {
		req.getSession().invalidate();
	}
}
