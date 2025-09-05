package com.back.domain.post.postComment.event

import com.back.domain.post.post.dto.PostDto
import com.back.domain.post.postComment.dto.PostCommentDto
import com.back.domain.post.postUser.dto.PostUserDto

data class PostCommentWrittenEvent(
    val postCommentDto: PostCommentDto,
    val postDto: PostDto,
    val actorDto: PostUserDto
) {

}