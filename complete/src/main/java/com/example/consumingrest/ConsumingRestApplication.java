package com.example.consumingrest;

import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.bouncycastle.crypto.agreement.srp.SRP6StandardGroups;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.SecureRandom;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static byte[] hextToBytes(String hex) {
		return Hex.decode(hex);
	}

	public static BigInteger hextToBigInt(String hex) {
		return new BigInteger(1, Hex.decode(hex));
	}

	public static String bigIntToHex(BigInteger bigInt) {
		return Hex.toHexString(bigInt.toByteArray());
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			UserServerCredsModel model = restTemplate.getForObject("http://localhost:8080/auth/challenge?login=test", UserServerCredsModel.class);
			log.info("Challenge response: {}", model);

			SRP6Client srp6Client = new SRP6Client();
			srp6Client.init(
					SRP6StandardGroups.rfc5054_2048,
					DigestFactory.createSHA3_512(),
					new SecureRandom()
			);
			final BigInteger A = srp6Client.generateClientCredentials(
					hextToBytes(model.getS()),
					"test".getBytes(),
					"secret".getBytes());
			final BigInteger S = srp6Client.calculateSecret(hextToBigInt(model.getB()));
			final BigInteger M1 = srp6Client.calculateClientEvidenceMessage();

			log.info("A: {}", bigIntToHex(A));
			log.info("S: {}", bigIntToHex(S));
			log.info("M1: {}", bigIntToHex(M1));

			AuthenticationModel authModel = restTemplate.getForObject("http://localhost:8080/auth/authenticate?login=test&A={A}&M1={M1}", AuthenticationModel.class, bigIntToHex(A), bigIntToHex(M1));
			log.info("Authentication response: {}", authModel);

			boolean sev = srp6Client.verifyServerEvidenceMessage(hextToBigInt(authModel.getM2()));
			log.info("Server evidence validated: {}", sev);
//			Quote quote = restTemplate.getForObject(
//					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//			log.info(quote.toString());
		};
	}
}
