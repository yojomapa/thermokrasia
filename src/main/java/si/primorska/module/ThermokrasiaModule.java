package si.primorska.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import org.skife.jdbi.v2.DBI;

import si.primorska.db.SampleDao;
import si.primorska.db.TemperatureDao;
import si.primorska.service.SampleService;
import si.primorska.service.SampleServiceImpl;
import si.primorska.service.TemperatureService;
import si.primorska.service.TemperatureServiceImpl;

public class ThermokrasiaModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(TemperatureService.class).to(TemperatureServiceImpl.class);
    bind(SampleService.class).to(SampleServiceImpl.class);
  }

  @Provides
  @Singleton
  public TemperatureDao provideTemperatureDao(DBI dbi) {
    return dbi.onDemand(TemperatureDao.class);
  }


  @Provides
  @Singleton
  public SampleDao provideSampleDao(DBI dbi) {
    return dbi.onDemand(SampleDao.class);
  }
}
