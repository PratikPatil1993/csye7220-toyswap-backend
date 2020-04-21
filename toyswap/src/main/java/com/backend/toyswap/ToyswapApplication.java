package com.backend.toyswap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.backend.toyswap.model.Toy;
import com.backend.toyswap.model.User;
import com.backend.toyswap.repository.ToyRepository;
import com.backend.toyswap.repository.UserRepository;
import com.backend.toyswap.service.AuthService;
import com.backend.toyswap.service.ToyService;
import com.backend.toyswap.service.UserService;


@SpringBootApplication
public class ToyswapApplication {
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
	public static void main(String[] args) {
		SpringApplication.run(ToyswapApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE",
						"OPTIONS");
			}
		};
	}

	@Bean
	public CommandLineRunner demo(ToyService toyService, UserRepository userRepository, UserService userService) {
		return (args) -> {
			
			// save a few toys with users
			userService.register(new User("bob@neu.edu", "bob@1234", "Bob"));
//			User user = userService.findUserByEmail("bob@neu.edu");
//			MultipartFile multipart = new 
//			toyService.create(new Toy("NERF N-Strike Elite Delta Power", 429,
//					"toy_images/NERF_N_Strike_Elite_Delta_Power.jpg", user, "toy_images/NERF_N_Strike_Elite_Delta_Power.jpg"));
//			toyService.create(new Toy("Monopoly: Cheater’s Edition SE", 299,
//					"toy_images/Monopoly_Cheaters_Edition_SE.jpg", user, null));
//			
			userService.register(new User("ori@neu.edu", "ori@1234", "Ori"));
//			user = userService.findUserByEmail("ori@neu.edu");
//			toyService.create(new Toy("Cruiser 16\" Cykel Gul", 699,
//					"toy_images/Cruiser_16_Cykel_Gul.jpg", user, null ));
//			toyService.create(new Toy("Colorful Cupcakes Pussel 500 Delar", 199,
//					"toy_images/Colorful_Cupcakes_Pussel_500_Delar.jpg", user, null));
			
			userService.register(new User("mark@neu.edu", "mark@1234", "Mark"));
//			user = userService.findUserByEmail("mark@neu.edu");
//			toyService.create(new Toy("Jätte Plockepinn", 199,
//					"toy_images/Jatte_Plockepinn.jpg", user, null));
//			toyService.create(new Toy("LEGO® Star Wars™ Action Battle Hoth™ Generator Attack", 257,
//					"toy_images/LEGO_Star_Wars_Action_Battle_Hoth.jpg", user, null));
			
			userService.register(new User("sam@neu.edu", "sam@1234", "Sam"));
//			user = userService.findUserByEmail("sam@neu.edu");
//			toyService.create(new Toy("Sparkcykel Classic Big Wheel Vit", 399,
//					"toy_images/Sparkcykel_Classic_Big_Wheel_Vit.jpg", user, null));
//			toyService.create(new Toy("Fab Lab Face Tattoo Klistertatueringar", 199,
//					"toy_images/Fab_Lab_Face_Tattoo.jpg", user, null));
			
		};
	}
	
//	@Bean
//	public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
//	    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//	    resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
//	    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//	    dataSourceInitializer.setDataSource(dataSource);
//	    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//	    return dataSourceInitializer;
//	}

}
