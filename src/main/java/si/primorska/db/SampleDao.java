package si.primorska.db;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import si.primorska.api.Sample;



public interface SampleDao {

  @SqlUpdate("insert into sample (start_time, end_time) values (:startTime, :endTime)")
  @GetGeneratedKeys
  Long insert(@BindBean Sample sample);


  @SqlUpdate("update sample set end_time = :endTime where id = :id")
  void update(@BindBean Sample sample);

}
