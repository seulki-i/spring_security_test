package test.dev.web.common.config.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author skkim
 * @since 2021-04-21
 */
@Configuration
@Log4j2
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("web/index");
        registry.addViewController("/login").setViewName("web/login");

//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); // 우선순위를 가장 높게 잡는다.
    }
}
