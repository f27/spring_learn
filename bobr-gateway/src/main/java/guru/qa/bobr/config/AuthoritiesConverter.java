package guru.qa.bobr.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.log.LogMessage;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.*;

public class AuthoritiesConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final String DEFAULT_AUTHORITIES_CLAIM_DELIMITER = " ";
    private static final Map<String, String> MY_AUTHORITIES = new HashMap<>() {{
        put("scope", "SCOPE_");
        put("scp", "SCOPE_");
        put("role", "ROLE_");
    }};
    private final Log logger = LogFactory.getLog(getClass());
    private Collection<String> authoritiesClaimNames;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        return new JwtAuthenticationToken(source, extractGrantedAuthorities(source));
    }

    private Collection<GrantedAuthority> extractGrantedAuthorities(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String authority : getAuthorities(jwt)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }

    private Collection<String> getAuthoritiesClaimNames(Jwt jwt) {
        if (this.authoritiesClaimNames != null) {
            return this.authoritiesClaimNames;
        }
        authoritiesClaimNames = new ArrayList<>();
        for (String claimName : MY_AUTHORITIES.keySet()) {
            if (jwt.hasClaim(claimName)) {
                authoritiesClaimNames.add(claimName);
            }
        }
        return authoritiesClaimNames;
    }

    private Collection<String> getAuthorities(Jwt jwt) {
        Collection<String> claimNames = getAuthoritiesClaimNames(jwt);
        Collection<String> allAuthorities = new ArrayList<>();
        if (claimNames == null) {
            this.logger.trace("Returning no authorities since could not find any claims that might contain authority");
            return Collections.emptyList();
        }
        for (String claimName : claimNames) {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace(LogMessage.format("Looking for scopes in claim %s", claimName));
            }
            Object authoritiesRaw = jwt.getClaim(claimName);
            String authorityPrefix = MY_AUTHORITIES.get(claimName);
            if (authoritiesRaw instanceof String) {
                if (StringUtils.hasText((String) authoritiesRaw)) {
                    String[] authorities = ((String) authoritiesRaw).split(DEFAULT_AUTHORITIES_CLAIM_DELIMITER);
                    for (String authority : authorities) {
                        allAuthorities.add(authorityPrefix + authority);
                    }
                }
            }
            if (authoritiesRaw instanceof Collection) {
                Collection<String> authorities = castAuthoritiesToCollection(authoritiesRaw);
                for (String authority : authorities) {
                    allAuthorities.add(authorityPrefix + authority);
                }
            }
        }
        return allAuthorities;
    }


    @SuppressWarnings("unchecked")
    private Collection<String> castAuthoritiesToCollection(Object authorities) {
        return (Collection<String>) authorities;
    }
}
