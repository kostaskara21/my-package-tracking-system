package com.kara.tracking.system.service;

import reactor.core.publisher.Mono;

public interface AuthUserService {

    Mono<Void> authenticateUser(String userId);
}
