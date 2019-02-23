package com.example.samplespringsecurityredis

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class SimpleLoginUser(user: User) : User(
        user.username,
        user.password,
        true,
        true,
        true,
        true,
        determineRoles()) {
    companion object {
        private fun determineRoles(): List<GrantedAuthority> {
            return ADMIN_ROLES
        }
    }
}