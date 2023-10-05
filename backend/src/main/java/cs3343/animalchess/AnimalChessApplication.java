package cs3343.animalchess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class AnimalChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalChessApplication.class, args);
	}

}
