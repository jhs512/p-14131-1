package com.back.domain.post.postUser.service

import com.back.domain.post.postUser.entity.PostUser
import com.back.domain.post.postUser.repository.PostUserAttrRepository
import org.springframework.stereotype.Service

@Service
class PostUserAttrService(
    private val postUserAttrRepository: PostUserAttrRepository,
) {
    fun findBySubjectAndName(subject: PostUser, name: String) =
        postUserAttrRepository.findBySubjectAndName(subject, name)
}
