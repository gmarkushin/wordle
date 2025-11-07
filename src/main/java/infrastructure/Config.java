package infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {
		"api.apiLogic",
		"api.console",
		"domain.game",
		"domain.service",
		"infrastructure.filePars"
})
public class Config {
}