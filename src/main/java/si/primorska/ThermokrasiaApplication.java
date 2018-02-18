package si.primorska;

import com.hubspot.dropwizard.guice.GuiceBundle;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.DBIHealthCheck;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import si.primorska.db.SampleDao;
import si.primorska.db.TemperatureDao;
import si.primorska.module.ThermokrasiaModule;
import si.primorska.resources.TemperatureResource;

public class ThermokrasiaApplication extends Application<ThermokrasiaConfiguration> {

    private GuiceBundle<ThermokrasiaConfiguration> guiceBundle;

    public static void main(final String[] args) throws Exception {
        new ThermokrasiaApplication().run(args);
    }

    @Override
    public String getName() {
        return "Thermokrasia";
    }

    @Override
    public void initialize(final Bootstrap<ThermokrasiaConfiguration> bootstrap) {

      guiceBundle = GuiceBundle.<ThermokrasiaConfiguration>newBuilder()
          .addModule(new ThermokrasiaModule())
          .setConfigClass(ThermokrasiaConfiguration.class)
          .build();

      bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final ThermokrasiaConfiguration configuration,
                    final Environment environment) {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        environment.healthChecks().register("mysql", new DBIHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));
        environment.jersey().register(TemperatureResource.class);
    }

}
