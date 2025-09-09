package com.back.domain.post.postUser.entity

import com.back.global.jpa.entity.BaseTime
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["subject_id", "name"])
    ]
)
class PostUserAttr(
    @field:ManyToOne(fetch = LAZY)
    @field:JoinColumn(name = "subject_id")
    val subject: PostUser,
    val name: String,
    @field:Column(name = "val", columnDefinition = "TEXT")
    var value: String,
) : BaseTime() {

}
