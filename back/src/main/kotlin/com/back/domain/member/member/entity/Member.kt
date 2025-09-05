package com.back.domain.member.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.*

@Entity
class Member(
    id: Int,
    username: String,
    var password: String? = null,
    var nickname: String,
    @field:Column(unique = true) var apiKey: String,
    profileImgUrl: String? = null,
) : BaseMember(id, username, profileImgUrl) {
    // JPA와 상관없는 객체를 만들 때 사용, SecurityUser로부터 정보를 받아와서 생성할 때 사용
    constructor(id: Int, username: String, nickname: String) : this(
        id,
        username,
        null,
        nickname,
        ""
    )

    constructor(username: String, password: String?, nickname: String, profileImgUrl: String?) : this(
        0,
        username,
        password,
        nickname,
        UUID.randomUUID().toString(), // apiKey는 UUID로 생성
        profileImgUrl
    )

    val name: String
        get() = nickname

    fun modify(nickname: String, profileImgUrl: String?) {
        this.nickname = nickname
        this.profileImgUrl = profileImgUrl
    }

    fun modifyApiKey(apiKey: String) {
        this.apiKey = apiKey
    }
}
