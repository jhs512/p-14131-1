package com.back.domain.post.postUser.service

import com.back.standard.extensions.getOrThrow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostUserAttrServiceTest {
    @Autowired
    private lateinit var postUserService: PostUserService

    @Autowired
    private lateinit var postUserAttrService: PostUserAttrService

    @Test
    @DisplayName("postsCount 증가")
    fun t1() {
        val postUser1 = postUserService.findByUsername("user1").getOrThrow()
        postUserAttrService.incrementPostsCount(postUser1)
    }

    @Test
    @DisplayName("postsCount 감소")
    fun t2() {
        val postUser1 = postUserService.findByUsername("user1").getOrThrow()
        postUserAttrService.decrementPostsCount(postUser1)
    }

    @Test
    @DisplayName("postCommentsCount 증가")
    fun t3() {
        val postUser1 = postUserService.findByUsername("user1").getOrThrow()
        postUserAttrService.incrementPostCommentsCount(postUser1)
    }

    @Test
    @DisplayName("postCommentsCount 감소")
    fun t4() {
        val postUser1 = postUserService.findByUsername("user1").getOrThrow()
        postUserAttrService.decrementPostCommentsCount(postUser1)
    }

    @Test
    @DisplayName("postsCount 조회")
    fun t5() {
        val postUser1 = postUserService.findByUsername("user1").getOrThrow()
        postUserAttrService.getPostsCount(postUser1)
    }

    @Test
    @DisplayName("postCommentsCount 조회")
    fun t6() {
        val postUser1 = postUserService.findByUsername("user1").getOrThrow()
        postUserAttrService.getPostCommentsCount(postUser1)
    }
}