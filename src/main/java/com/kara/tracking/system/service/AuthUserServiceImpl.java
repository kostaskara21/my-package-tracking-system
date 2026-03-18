package com.kara.tracking.system.service;

import com.kara.tracking.system.exceptions.AuthenticationFailedException;
import com.kara.tracking.system.exceptions.ClientErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService{

    private final WebClient authWebClient;

    @Override
    public Mono<Void> authenticateUser(String userId) {
        return authWebClient.get()
                .uri("/auth/authenticate/{authNumber}", userId)
                .retrieve()

                //TODO IMPLEMENTATION ON API MUST CHANGE NOW WE DO NOT KNOW IF USER IS UNAUTH OR OTHER REASON CAUSED BAD REQUEST
                .onStatus(status -> status == HttpStatus.BAD_REQUEST, response ->
                        Mono.error(new AuthenticationFailedException("User not authenticated"))
                )
                .onStatus(status -> status == HttpStatus.INTERNAL_SERVER_ERROR, response ->
                        Mono.error(new ClientErrorException("Server error: " + response.statusCode()))
                )
                .toBodilessEntity()
                .then()
                .doOnSuccess(response -> System.out.println("equest successful for user: " + userId))
                .doOnError(error -> System.err.println("Request failed: " + error.getMessage()))
                //eg DNS failure  app can not reach base url
                .onErrorMap(ex -> {
                    if (ex instanceof WebClientRequestException) {
                        return new RuntimeException("Connection failed", ex);
                    }
                    return ex;
                });
    }
}
