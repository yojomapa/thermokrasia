package si.primorska.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import si.primorska.api.Temperature;
import si.primorska.service.TemperatureService;

@Path("/temperature")
public class TemperatureResource {

  private TemperatureService temperatureService;

  @Inject
  public TemperatureResource(TemperatureService temperatureService) {
    this.temperatureService = temperatureService;
  }

  @POST
  public Response save(Temperature temperature) {
    temperatureService.save(temperature);
    return Response.ok().build();
  }

}
