package com.back.domain.member.member.entity

import com.back.domain.member.member.repository.MemberAttrRepository
import com.back.global.jpa.entity.BaseEntity
import com.back.global.jpa.entity.BaseTime
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.NaturalId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

@MappedSuperclass
class BaseMember(
    id: Int,
    @field:NaturalId @field:Column(unique = true) val username: String,
    var profileImgUrl: String? = null,
) : BaseTime(id) {
    companion object {
        lateinit var memberAttrRepository: MemberAttrRepository
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other == null || other !is BaseMember) return false
        val that = other as BaseEntity
        return id == that.id
    }

    val redirectToProfileImgUrlOrDefault: String
        get() = "http://localhost:8080/api/v1/members/${id}/redirectToProfileImg"

    val profileImgUrlOrDefault: String
        get() {
            profileImgUrl?.let { return it }

            return "https://placehold.co/600x600?text=U_U"
        }

    val isAdmin: Boolean
        get() {
            if ("system" == username) return true
            if ("admin" == username) return true

            return false
        }

    val authoritiesAsStringList: List<String>
        get() {
            val authorities = mutableListOf<String>()

            if (isAdmin) authorities.add("ROLE_ADMIN")

            return authorities
        }

    val authorities: Collection<GrantedAuthority>
        get() = authoritiesAsStringList.map { SimpleGrantedAuthority(it) }
}
