package pers.georgedon.susufile.fileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.georgedon.susufile.fileserver.server.FileServer;

//@SpringBootApplication
public class SusufileApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SusufileApplication.class, args);
		FileServer server=new FileServer();
		server.init();
	}

}
