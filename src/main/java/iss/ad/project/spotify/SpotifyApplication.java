package iss.ad.project.spotify;

import iss.ad.project.spotify.repo.AdminRepo;
import iss.ad.project.spotify.model.Admin;
import iss.ad.project.spotify.service.ClusterService;
import iss.ad.project.spotify.service.SpotifyService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
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
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Component
	public class UserCommandLineRunner implements CommandLineRunner {

		@Resource
		private AdminRepo adminRepo;
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;


		@Override
		public void run(String... args) throws Exception {
			Admin[] admins = {
					 new Admin(1L, "adm_1_John", passwordEncoder.encode("adminpass1")),
					 new Admin(2L, "adm_2_Jane", passwordEncoder.encode("adminpass2"))
			};
			for (Admin admin : admins) {
				adminRepo.save(admin);
			}

			System.out.println("Admin entities created and saved.");
		}
	}
}
