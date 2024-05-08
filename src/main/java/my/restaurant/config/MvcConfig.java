package my.restaurant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
	// Este método está vazio porque não requer configuração adicional no momento.
    // Se configurações adicionais forem necessárias no futuro, elas podem ser adicionadas aqui.
    // Por enquanto, não há ação necessária.
    }
    
}
