package si.primorska.db;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import si.primorska.api.Temperature;

public interface TemperatureDao {

  @SqlUpdate("insert into temperature (sensor_id, sensed_date, temp, temp_unit) values (:sensorId, :sensedDate, :temp, :tempUnit)")
  void insert(@BindBean Temperature temperature);

}
