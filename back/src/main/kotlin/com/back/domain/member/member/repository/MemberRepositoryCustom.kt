package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member

interface MemberRepositoryCustom {
    fun findQById(id: Int): Member?
    fun findQByUsername(username: String): Member?
    fun findQByIdIn(ids: List<Int>): List<Member>
}