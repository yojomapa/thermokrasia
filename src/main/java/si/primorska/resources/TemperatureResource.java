package si.primorska.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import si.primorska.api.Temperature;
import si.primorska.db.TemperatureDao;
import si.primorska.service.TemperatureService;

@Path("/temperature")
public class TemperatureResource {

  private TemperatureDao temperatureDao;

  public TemperatureResource(TemperatureDao temperatureDao) {
    this.temperatureDao = temperatureDao;
  }

  @POST
  public Response save(Temperature temperature) {
    temperatureDao.insert(temperature);
    return Response.ok().build();
  }

}
