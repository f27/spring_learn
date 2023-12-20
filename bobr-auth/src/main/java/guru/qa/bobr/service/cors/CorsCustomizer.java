package guru.qa.bobr.service.cors;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
public class CorsCustomizer {

    private final String bobrFrontUri;
    private final String bobrAuthUri;

    @Autowired
    public CorsCustomizer(@Value("${bobr-front.base-uri}") String bobrFrontUri,
                          @Value("${bobr-auth.base-uri}") String bobrAuthUri) {
        this.bobrFrontUri = bobrFrontUri;
        this.bobrAuthUri = bobrAuthUri;
    }

    public void corsCustomizer(@Nonnull HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowCredentials(true);
                cc.setAllowedOrigins(List.of(bobrFrontUri, bobrAuthUri));
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowedMethods(List.of("*"));
                return cc;
            };

            c.configurationSource(source);
        });
    }
}
