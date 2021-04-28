package test.dev.web.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author skkim
 * @since 2021. 04. 23.
 */
@Configuration
public class ModelConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
