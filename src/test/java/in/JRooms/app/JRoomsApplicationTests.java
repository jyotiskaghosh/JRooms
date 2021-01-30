package in.JRooms.app;

import in.JRooms.app.models.User;
import in.JRooms.app.repositories.UserRepository;
import io.rsocket.metadata.WellKnownMimeType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.rsocket.metadata.SimpleAuthenticationEncoder;
import org.springframework.security.rsocket.metadata.UsernamePasswordMetadata;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JRoomsApplicationTests {

	private static RSocketRequester requester;
    private static final User user = new User();
    private final UsernamePasswordMetadata credentials = new UsernamePasswordMetadata("tester", "password");
    private final MimeType mimeType = MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.getString());

    @BeforeAll
    public static void setupOnce(@Autowired UserRepository userRepository,
                                 @Autowired PasswordEncoder passwordEncoder) {
        user.setUsername("tester");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(Set.of("USER"));

        userRepository.save(user).subscribe();

        requester = RSocketRequester.builder()
                .rsocketStrategies(s -> s.encoder(new SimpleAuthenticationEncoder()))
                .tcp("localhost", 7000);
    }
	@Test
	public void RSocketTest() {
		Mono<String> receive = requester.route("test")
                .metadata(this.credentials, this.mimeType)
				.data("Testing")
				.retrieveMono(String.class);
		StepVerifier
				.create(receive)
                .consumeNextWith(msg -> assertThat(msg.equals("Testing")))
				.verifyComplete();
	}

	@AfterAll
	public static void tearDownOnce(@Autowired UserRepository userRepository) {
        userRepository.delete(user).subscribe();
	}
}
