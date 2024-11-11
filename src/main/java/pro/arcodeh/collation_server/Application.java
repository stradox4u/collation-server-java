package pro.arcodeh.collation_server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pro.arcodeh.collation_server.seeder.PollingUnitSeeder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
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
