package com.hamryt.helparty.service.gym

import com.hamryt.helparty.dto.gym.GymDTO
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse

interface GymService {

    fun insertGym(resource: SignUpGymRequest): SignUpGymResponse

    fun findGymByEmailAndPassword(email: String, password: String): GymDTO

    fun updateGym(loginId: Long, updateGymRequest: UpdateGymRequest): UpdateGymResponse

    fun deleteGym(loginId: Long)
}