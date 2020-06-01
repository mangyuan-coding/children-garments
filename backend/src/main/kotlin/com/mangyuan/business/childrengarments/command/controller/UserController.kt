package com.mangyuan.business.childrengarments.command.controller

import com.mangyuan.business.childrengarments.api.CreateUserCommand
import com.mangyuan.business.childrengarments.api.UserId
import com.mangyuan.business.childrengarments.command.controller.dto.CreateUser
import lombok.RequiredArgsConstructor
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequiredArgsConstructor
class UserController(private var commandGateway: CommandGateway) {

    @PostMapping("/users")
    fun saveUser(@RequestBody createUser: Mono<CreateUser>): Mono<Boolean> {

        return createUser
                .map { user ->
                    CreateUserCommand(UserId(UUID.randomUUID().toString()),
                            user.name, user.account, user.password)
                }
                .map { command -> commandGateway.send<CreateUserCommand>(command) }
                .publish { Mono.just(true) }
    }
}