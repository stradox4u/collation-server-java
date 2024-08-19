package pro.arcodeh.collation_server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pro.arcodeh.collation_server.seeder.PollingUnitSeeder;

@SpringBootApplication
public class CollationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollationServerApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PollingUnitSeeder puSeeder) {
		return args -> {
			boolean shouldSeed = puSeeder.shouldSeed();
			if(shouldSeed) {
				puSeeder.run();
			}
		};
	}

}
