package tuk.bitong.marn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "tuk.bitong.marn.domain")
@EntityScan(basePackages = "tuk.bitong.marn.domain")
public class Application extends SpringBootServletInitializer {

	///public static String ROOT_UPLOAD =  Application.class.getClassLoader().getResource("./static") + "/upload-dir";
	//public static String ROOT_UPLOAD =  "src/main/resources/static/upload-dir";

	public static String CLASSPATH_UPLOAD_DIR ="classpath:static/upload-dir";

	public static void main(String[] args) {
//		org.springframework.security.crypto.password.PasswordEncoder encoder
//				= new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
//
//		// $2a$10$lB6/PKg2/JC4XgdMDXyjs.dLC9jFNAuuNbFkL9udcXe/EBjxSyqxW
//		// true
//		// $2a$10$KbQiHKTa1WIsQFTQWQKCiujoTJJB7MCMSaSgG/imVkKRicMPwgN5i
//		// true
//		// $2a$10$5WfW4uxVb4SIdzcTJI9U7eU4ZwaocrvP.2CKkWJkBDKz1dmCh50J2
//		// true
//		// $2a$10$0wR/6uaPxU7kGyUIsx/JS.krbAA9429fwsuCyTlEFJG54HgdR10nK
//		// true
//		// $2a$10$gfmnyiTlf8MDmwG7oqKJG.W8rrag8jt6dNW.31ukgr0.quwGujUuO
//		// true
//
//		for (int i = 0; i < 5; i++) {
//			// "123456" - plain text - user input from user interface
//			String passwd = encoder.encode("123456");
//
//			// passwd - password from database
//			System.out.println(passwd); // print hash
//
//			// true for all 5 iteration
//			System.out.println(encoder.matches("123456", passwd));
//		}
		SpringApplication.run(Application.class, args);
	}

	/*@Bean
	CommandLineRunner init() {
		return (String[] args) -> {
			System.out.println(ROOT_UPLOAD);
			new File(ROOT_UPLOAD).mkdir();
		};
	}*/

}
