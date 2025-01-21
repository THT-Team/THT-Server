package com.tht.thtapis.dev

import com.tht.domain.entity.like.UserLikeRepository
import com.tht.domain.entity.user.repository.UserDailyFallingRepository
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
class DevController(
    private val userLikeRepository: UserLikeRepository,
    private val userDailyFallingRepository: UserDailyFallingRepository
) {

    @PostMapping("/like-and-dislike/reset")
    fun resetLikeData(authentication: Authentication?): ResponseEntity<Objects> {
        val userUuid: String = authentication?.credentials.toString()
        userLikeRepository.deleteAllByUserUuid(userUuid)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PostMapping("/selected-talk-keywords/reset")
    fun resetSelectedFallingKeyword(authentication: Authentication?): ResponseEntity<Objects> {
        val userUuid: String = authentication?.credentials.toString()
        userDailyFallingRepository.deleteAllByUserUuid(userUuid)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}