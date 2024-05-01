package com.desertlamp.configserver.config;

import org.springframework.boot.actuate.info.Info.Builder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ConfigServerInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		Map<String, Object> infoDetails = new HashMap<>();
		infoDetails.put("application","DESERT-LAMP-CONFIG-SERVER");
		builder.withDetail("Config-Server-Details", infoDetails);
	}

}
