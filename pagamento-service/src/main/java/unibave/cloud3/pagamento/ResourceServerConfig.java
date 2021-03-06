package unibave.cloud3.pagamento;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest().authenticated();
    }

//    @Bean
//    public OAuth2FeignRequestInterceptor feignRequestInterceptor(
//            OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails resource) {
//        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, resource);
//    }

}
