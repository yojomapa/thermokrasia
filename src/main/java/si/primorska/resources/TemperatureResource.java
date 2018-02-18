package si.primorska.resources;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import si.primorska.api.Temperature;
import si.primorska.db.TemperatureDao;

@Path("/temperature")
public class TemperatureResource {

  private TemperatureDao temperatureDao;

  public TemperatureResource(TemperatureDao temperatureDao) {
    this.temperatureDao = temperatureDao;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response save(@NotNull @QueryParam("sensorId") String sensorId, @NotNull @QueryParam("temp") Double temp) {

    Temperature temperature = new Temperature();
    temperature.setSensedDate(LocalDateTime.now());
    temperature.setSensorId(sensorId);
    temperature.setTemp(temp);

    temperatureDao.insert(temperature);
    return Response.ok().build();
  }

}
