package in.JRooms.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JRoomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JRoomsApplication.class, args);
	}
}
