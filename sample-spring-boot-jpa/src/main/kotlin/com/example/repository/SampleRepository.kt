package com.example.repository

import com.example.entity.SampleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<SampleEntity, Int>