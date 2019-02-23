package com.example.samplespringsecurityredis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // AUTHORIZE
        http
            .authorizeRequests()
                .antMatchers("/pub/**")
                    .permitAll()
                .antMatchers("/auth/**")
                    .permitAll()
            .anyRequest()
                .authenticated()

        http.csrf().disable()  //CSRFを無効

        // LOGIN
        http
            .formLogin()
                .loginProcessingUrl("/auth/login")
                    .permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())

        // LOGOUT
        http
            .logout()
                .logoutRequestMatcher( AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("JSESSIONID", "SESSION")
                .invalidateHttpSession(true)
                .permitAll()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder,
                        @Qualifier("userDetailsService") userDetailsService: UserDetailsService,
                        passwordEncoder: PasswordEncoder
    ) {
        auth.eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun authenticationSuccessHandler(): AuthenticationSuccessHandler {
        return SimpleAuthenticationSuccessHandler()
    }

    fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return SimpleAuthenticationFailureHandler()
    }

    fun logoutSuccessHandler(): LogoutSuccessHandler {
        return HttpStatusReturningLogoutSuccessHandler()
    }
}

internal class SimpleAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(request: HttpServletRequest,
                                         response: HttpServletResponse,
                                         authentication: Authentication) {
        // HTTP Statusは200
        response.status = HttpServletResponse.SC_OK;

        // Content-Type: application/json
        response.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE

        // Body
        response.writer.write("OK!!")
        response.writer.flush()
    }
}

internal class SimpleAuthenticationFailureHandler : AuthenticationFailureHandler {
    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(request: HttpServletRequest,
                                         response: HttpServletResponse,
                                         exception: AuthenticationException) {
        // HTTP Statusは401
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        // Content-Type: application/json
        response.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE

        // Body
        response.writer.write("NG")
        response.writer.flush()
    }
}