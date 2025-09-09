package com.back.domain.post.postUser.entity

import com.back.domain.member.member.entity.BaseMember
import com.back.domain.member.member.entity.Member
import com.back.domain.post.postUser.service.PostUserAttrService
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.data.annotation.Immutable

@Entity
@Immutable
@Table(name = "member")
class PostUser(
    id: Int,
    username: String,
    @field:Column(name = "nickname") var name: String,
    profileImgUrl: String? = null,
) : BaseMember(id, username, profileImgUrl) {

    companion object {
        lateinit var attrService: PostUserAttrService
    }

    val postsCount: Int
        get() = attrService.findBySubjectAndName(this, "postsCount")?.value?.toInt() ?: 0

    val postCommentsCount: Int
        get() = attrService.findBySubjectAndName(this, "postCommentsCount")?.value?.toInt() ?: 0

    fun incrementPostsCount() {
        attrService.incrementPostsCount(this)
    }

    fun decrementPostsCount() {
        attrService.decrementPostsCount(this)
    }

    fun incrementPostCommentsCount() {
        attrService.incrementPostCommentsCount(this)
    }

    fun decrementPostCommentsCount() {
        attrService.decrementPostCommentsCount(this)
    }

    constructor(member: Member) : this(
        member.id,
        member.username,
        member.nickname,
        member.profileImgUrl,
    )
}