package si.primorska.service;

import javax.inject.Inject;

import si.primorska.api.Temperature;
import si.primorska.db.TemperatureDao;

public class TemperatureServiceImpl implements TemperatureService {

  private TemperatureDao temperatureDao;

  @Inject
  public TemperatureServiceImpl(TemperatureDao temperatureDao) {
    this.temperatureDao = temperatureDao;
  }

  @Override
  public void save(Temperature temperature) {
    temperatureDao.insert(temperature);
  }
}
