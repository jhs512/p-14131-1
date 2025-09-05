package com.back.domain.post.postUser.entity

import com.back.global.jpa.entity.BaseTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.NaturalId

@Entity
@Table(name = "member")
class PostUser(
    id: Int,
    @field:NaturalId @field:Column(unique = true) val username: String,
    @field:Column(name = "nickname") var name: String,
    var profileImgUrl: String? = null,
) : BaseTime(id) {

}
