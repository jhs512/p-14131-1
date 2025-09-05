package com.back.domain.post.postUser.entity

import com.back.domain.member.member.entity.BaseMember
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class PostUser(
    id: Int,
    username: String,
    @field:Column(name = "nickname") var name: String,
    profileImgUrl: String? = null,
) : BaseMember(id, username, profileImgUrl) {

}
