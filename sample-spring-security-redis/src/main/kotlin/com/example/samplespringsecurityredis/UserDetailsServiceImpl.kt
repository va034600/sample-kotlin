package com.example.samplespringsecurityredis

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

val ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")

@Component(value = "userDetailsService")
class UserDetailsServiceImpl: UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {

        return SimpleLoginUser(User(
                username,
                BCryptPasswordEncoder().encode("password"),
                ADMIN_ROLES))
    }
}
