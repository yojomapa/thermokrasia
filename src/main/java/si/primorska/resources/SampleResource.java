package si.primorska.resources;

import java.time.LocalDateTime;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import si.primorska.api.Sample;
import si.primorska.db.SampleDao;

@Path("/sample")
public class SampleResource {

  private SampleDao sampleDao;

  public SampleResource(SampleDao sampleDao) {
    this.sampleDao = sampleDao;
  }

  @POST
  public Response start() {
    Sample sample = new Sample();
    sample.setStartTime(LocalDateTime.now());
    Long id = sampleDao.insert(sample);
    return Response.ok(id).build();
  }

  @PUT
  @Path("/{id}")
  public Response stop(@PathParam("id") Long id) {
    Sample sample = new Sample();
    sample.setId(id);
    sample.setEndTime(LocalDateTime.now());
    sampleDao.update(sample);
    return Response.ok(id).build();
  }
}
