package com.desertlamp.eureka.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.info.Info.Builder;

@Component
public class EurekaServerInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		Map<String, Object> infoDetails = new HashMap<>();
		infoDetails.put("application","DESERT-LAMP-EUREKA-SERVER");
		builder.withDetail("Eureak-Server-Details", infoDetails);
	}
}
