package com.hospital_vm.cl.hospital_vm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HospitalVmApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalVmApplication.class, args);
		Dotenv dotenv = Dotenv.load();

	}

}
