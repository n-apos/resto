package com.workshops.resto.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {

    @Value($$"${jwt.secret}")
    private lateinit var secret: String

    @Value($$"${jwt.expiration}")
    private lateinit var expiration: String

    fun getUsername(token: String): String = getClaim(token, Claims::getSubject)

    fun getExpirationDate(token: String): Date = getClaim(token, Claims::getExpiration)

    fun <T> getClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = getAllClaims(token)
        return claimsResolver(claims)
    }

    private fun getAllClaims(token: String): Claims =
        Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload

    private fun isTokenExpired(token: String): Boolean {
        val expirationDate = getExpirationDate(token)
        return expirationDate.before(Date())
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        val now = System.currentTimeMillis()
        return Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(Date(now))
            .expiration(Date(now + expiration.toLong()))
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .compact()
    }

    fun validate(token: String): Boolean {
        return !isTokenExpired(token)
    }
}
