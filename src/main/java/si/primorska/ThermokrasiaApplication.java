package si.primorska;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.DBIHealthCheck;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.swagger.jaxrs.listing.ApiListingResource;
import si.primorska.db.SampleDao;
import si.primorska.db.TemperatureDao;
import si.primorska.resources.SampleResource;
import si.primorska.resources.TemperatureResource;

public class ThermokrasiaApplication extends Application<ThermokrasiaConfiguration> {

  public static void main(final String[] args) throws Exception {
    new ThermokrasiaApplication().run(args);
  }

  @Override
  public String getName() {
    return "Thermokrasia";
  }

  @Override
  public void initialize(final Bootstrap<ThermokrasiaConfiguration> bootstrap) {
    bootstrap.addBundle(new SwaggerBundle<ThermokrasiaConfiguration>() {
      @Override
      protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ThermokrasiaConfiguration configuration) {
        return configuration.getSwaggerBundleConfiguration();
      }
    });
  }

  @Override
  public void run(final ThermokrasiaConfiguration configuration,
                  final Environment environment) {

    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

    final TemperatureDao temperatureDao = jdbi.onDemand(TemperatureDao.class);
    final SampleDao sampleDao = jdbi.onDemand(SampleDao.class);
    final TemperatureResource temperatureResource = new TemperatureResource(temperatureDao);
    final SampleResource sampleResource = new SampleResource(sampleDao);
    environment.jersey().register(temperatureResource);
    environment.jersey().register(sampleResource);
    environment.jersey().register(new ApiListingResource());

    environment.healthChecks().register("mysql", new DBIHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));
  }

}
