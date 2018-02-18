package si.primorska.resources;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.ApiOperation;
import si.primorska.api.Temperature;
import si.primorska.db.TemperatureDao;

@Path("/temperature")
public class TemperatureResource {

  private TemperatureDao temperatureDao;

  public TemperatureResource(TemperatureDao temperatureDao) {
    this.temperatureDao = temperatureDao;
  }

  @POST
  @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
  @ApiOperation(
      value = "Create a new Temperature record for the given SensorId and Temp with current time in sensedDate property.")
  public Response save(@NotNull @QueryParam("sensorId") String sensorId, @NotNull @QueryParam("temp") Double temp, @QueryParam("unit") String unit) {

    Temperature temperature = new Temperature();
    temperature.setSensedDate(LocalDateTime.now());
    temperature.setSensorId(sensorId);
    temperature.setTemp(temp);
    temperature.setTempUnit(unit == null? 'C' : unit.toUpperCase().charAt(0)); // default is Centigrades

    temperatureDao.insert(temperature);
    return Response.ok().build();
  }

}
