package com.workshops.resto.configurations

import com.workshops.resto.util.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtTokenFilter @Autowired constructor(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsService // Spring will inject your UserDetailsService implementation
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = header.split(" ")[1].trim()
        if (!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request, response)
            return
        }

        val username = jwtTokenUtil.getUsername(token)
        val userDetails = userDetailsService.loadUserByUsername(username)

        val authentication = UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.authorities
        )

        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}
