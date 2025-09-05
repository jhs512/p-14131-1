package com.back.domain.member.member.entity

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
