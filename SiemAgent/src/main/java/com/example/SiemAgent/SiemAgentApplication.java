package com.example.SiemAgent;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.example.components")
public class SiemAgentApplication {
	static
	{
		File ts = new File("../SiemAgent/src/main/resources/jks/SiemAgent.jks");
		System.setProperty("javax.net.debug", "all");
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		System.setProperty("https.protocols", "TLSv1.2");
		System.setProperty("javax.net.ssl.trustStore", ts.getAbsolutePath());
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		System.setProperty("javax.net.ssl.keyStore",  ts.getAbsolutePath());
		System.setProperty("javax.net.ssl.keyStorePassword", "password");

		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				new javax.net.ssl.HostnameVerifier() {

					public boolean verify(String hostname,
										  javax.net.ssl.SSLSession sslSession) {
						System.out.println("\n\n 8888888888888888888888888888 \n\n");
						System.out.println(sslSession.getLocalCertificates());
						System.out.println("\n\n 8888888888888888888888888888 \n\n");
						if (hostname.equals("localhost")) {
							return true;
						}
						return false;
					}
				});
		
	}

	@Bean
	public RestTemplate template() throws Exception{
		RestTemplate template = new RestTemplate();
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(SiemAgentApplication.class, args);
	}

}
