package com.mark1708.botapicore.configuration.security;

import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.model.security.ApiBotKey;
import com.mark1708.botapicore.service.BotService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


@RequiredArgsConstructor
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    private final BotService botService;

    @Override
    public Authentication authenticate(
        Authentication authentication
    ) throws AuthenticationException {
        String authorization = (String) authentication.getCredentials();

        if(authorization != null && !authorization.isEmpty()) {
            try {
                Bot bot = botService.getBotByApiKey(authorization);
                return new ApiBotKey(
                    bot.getId(), null, new ArrayList<>()
                );
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ApiBotKey.class);
    }
}
