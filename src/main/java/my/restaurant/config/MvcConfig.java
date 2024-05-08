package my.restaurant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Construtor vazio utilizado para inicialização do componente de configuração.
     * Métodos de configuração podem ser adicionados conforme necessário para ajustar
     * as configurações do MVC no Spring.
     */
    public MvcConfig() {
	// Este método está vazio porque não requer configuração adicional no momento.
    // Se configurações adicionais forem necessárias no futuro, elas podem ser adicionadas aqui.
    // Por enquanto, não há ação necessária.
    }
    
}
