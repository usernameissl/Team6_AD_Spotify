package iss.ad.project.spotify;

import iss.ad.project.spotify.service.ClusterService;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpotifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotifyApplication.class, args);
	}

	@Bean
	public ApplicationRunner initializer(SpotifyService spotifyService, ClusterService clusterService) {
		return args -> {
			spotifyService.refreshCache();
			clusterService.refreshCache();
		};
	}
}
