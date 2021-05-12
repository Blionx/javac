package com.mercadolibre.pens_luis_bootcamp_final;

import com.mercadolibre.pens_luis_bootcamp_final.config.SpringConfig;
import com.mercadolibre.pens_luis_bootcamp_final.util.ScopeUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		new SpringApplicationBuilder(SpringConfig.class).registerShutdownHook(true)
				.run(args);
	}
}
