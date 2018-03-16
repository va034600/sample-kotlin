package com.example.service

import com.example.repository.SampleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SampleServiceImpl(
        val sampleRepository: SampleRepository
) : SampleService {
    @Transactional(readOnly = true)
    override fun getMessage(id:Int): String {
        val sampleEntity = sampleRepository.findById(id)
        return if(sampleEntity.isPresent) {
            sampleEntity.get().message ?: "empty column"
        } else "not found!!"
    }
}