package com.todoitserver.service

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.GetItemRequest
import com.google.auth.oauth2.TokenVerifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

// 사용자 정보 데이터를 담을 클래스
data class GoogleUserInfo(
    val id: String,
    val email: String,
    val name: String,
    val pictureUrl: String
)

@Service
class AuthService(private val amazonDynamoDB: AmazonDynamoDB) {
    @Value("\${google.clientId}")
    private lateinit var googleClientId: String

    fun verifyGoogleToken(token: String): GoogleUserInfo? {
        val userInfo = getGoogleUserInfo(token)

        if (userInfo != null && isUserSignUp(userInfo.id)) {
            return userInfo
        } else {
            return null
        }
    }

    private fun getGoogleUserInfo(token: String): GoogleUserInfo? {
        try {
            val tokenVerifier = TokenVerifier.newBuilder()
                .setAudience(googleClientId)
                .build()
            val googleToken = tokenVerifier.verify(token)
            val email = googleToken.payload["email"] as String
            val name = googleToken.payload["name"] as String
            val id = googleToken.payload["sub"] as String
            val pictureUrl = googleToken.payload["picture"] as String

            return GoogleUserInfo(id, email, name, pictureUrl)
        } catch (e: Error) {
            return null
        }
    }

    private fun isUserSignUp(userId: String): Boolean {
        val keyToGet = mutableMapOf<String, AttributeValue>()
        keyToGet["id"] = AttributeValue(userId)

        val getItemRequest = GetItemRequest("user", keyToGet)
        val item = amazonDynamoDB.getItem(getItemRequest).item
        return item != null && item.isNotEmpty()
    }
}