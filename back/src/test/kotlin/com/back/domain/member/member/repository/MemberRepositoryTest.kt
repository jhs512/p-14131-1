package com.back.domain.member.member.repository

import com.back.standard.extensions.getOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberRepositoryTest {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    @DisplayName("findById")
    fun t1() {
        val member = memberRepository.findById(1).get()

        assertThat(member.id).isEqualTo(1)
    }

    @Test
    @DisplayName("findQById")
    fun t2() {
        val member = memberRepository.findQById(1).getOrThrow()

        assertThat(member.id).isEqualTo(1)
    }

    @Test
    @DisplayName("findByUsername")
    fun t3() {
        val member = memberRepository.findByUsername("admin").getOrThrow()

        assertThat(member.username).isEqualTo("admin")
    }

    @Test
    @DisplayName("findQByUsername")
    fun t4() {
        val member = memberRepository.findQByUsername("admin").getOrThrow()

        assertThat(member.username).isEqualTo("admin")
    }
}