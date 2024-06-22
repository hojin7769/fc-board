package com.fastcampus.fcboard.service

import com.fastcampus.fcboard.exception.PostNotFundException
import com.fastcampus.fcboard.repository.PostRepository
import com.fastcampus.fcboard.service.dto.PostCreateRequestDto
import com.fastcampus.fcboard.service.dto.PostUpdateRequestDto
import com.fastcampus.fcboard.service.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
) {

    @Transactional
    fun createPost(requestDto: PostCreateRequestDto):Long{
        return postRepository.save(requestDto.toEntity()).id
    }

    @Transactional
    fun updatePost(id: Long, requestDto: PostUpdateRequestDto):Long{
        val post = postRepository.findByIdOrNull(id) ?: throw PostNotFundException()
        post.update(requestDto)
        return id
    }
}
