package com.mangyuan.business.childrengarments.security

import com.google.common.collect.Lists
import com.mangyuan.business.childrengarments.api.AuthenticateUserCommand
import com.mangyuan.business.childrengarments.query.model.UserView
import com.mangyuan.business.childrengarments.query.repository.UserViewRepository
import org.axonframework.commandhandling.callbacks.FutureCallback
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.interceptors.JSR303ViolationException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutionException

@Component
class TraderAuthenticationProvider(private var userViewRepository: UserViewRepository,
                                   private var commandGateway: CommandGateway) : AuthenticationProvider {

    private var userAuthorities: Collection<GrantedAuthority> =
            Lists.newArrayList(SimpleGrantedAuthority("ROLE_USER"))


    override fun authenticate(authentication: Authentication): Authentication? {
        if (!supports(authentication.javaClass)) {
            return null
        }
        val token = authentication as UsernamePasswordAuthenticationToken
        val username = token.name
        val password = token.credentials.toString()
        val authenticatedCallback: FutureCallback<AuthenticateUserCommand, Boolean> = FutureCallback()
        val user: UserView = userViewRepository.findByAccount(username)
        val command = AuthenticateUserCommand(user.getUserId(), username, password.toCharArray())
        try {
            commandGateway.send(command, authenticatedCallback)
            // the bean validating interceptor is defined as a dispatch interceptor, meaning it is executed before
            // the command is dispatched.
        } catch (e: JSR303ViolationException) {
            return null
        }
        val account: UserAccount?
        try {
            if (authenticatedCallback.get() as Boolean) {
                throw BadCredentialsException("Invalid username and/or password")
            }
            account = user
        } catch (e: InterruptedException) {
            throw AuthenticationServiceException("Credentials could not be verified", e)
        } catch (e: ExecutionException) {
            throw AuthenticationServiceException("Credentials could not be verified", e)
        }

        val result = UsernamePasswordAuthenticationToken(account, authentication.getCredentials(), userAuthorities)
        result.details = authentication.getDetails()
        return result
    }

    override fun supports(p0: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(p0)
    }
}