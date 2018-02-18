package si.primorska.resources;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.ApiOperation;
import si.primorska.api.Sample;
import si.primorska.db.SampleDao;

@Path("/sample")
public class SampleResource {

  private SampleDao sampleDao;

  public SampleResource(SampleDao sampleDao) {
    this.sampleDao = sampleDao;
  }

  @POST
  @ApiOperation(
      value = "Star a new Sample with current time in startTime",
      notes = "This method will return the id of the new Sample. Tne new Sample has endTime = null.")
  public Response start() {
    Sample sample = new Sample();
    sample.setStartTime(LocalDateTime.now());
    Long id = sampleDao.insert(sample);
    return Response.ok(id).build();
  }

  @PUT
  @Path("/{id}")
  @ApiOperation(
      value = "Stop the Sample identified with the given Id",
      notes = "This method will add the current time to Sample endTime property. This method will return the id of the Sample.")
  public Response stop(@NotNull @PathParam("id") Long id) {
    Sample sample = new Sample();
    sample.setId(id);
    sample.setEndTime(LocalDateTime.now());
    sampleDao.update(sample);
    return Response.ok(id).build();
  }
}
