package com.hamryt.helparty.controller

import com.hamryt.helparty.argumentresolver.LoginId
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest
import com.hamryt.helparty.interceptor.LoginValidation
import com.hamryt.helparty.service.gym.GymService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("gyms")
class GymController(private val gymService: GymService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signUpGym(@Valid @RequestBody resource: SignUpGymRequest) =
        gymService.insertGym(resource)

    @LoginValidation
    @PutMapping
    fun updateGym(@LoginId loginId: Long, @Valid @RequestBody updateGymRequest: UpdateGymRequest) =
        gymService.updateGym(loginId, updateGymRequest)

    @LoginValidation
    @DeleteMapping
    fun deleteGym(@LoginId loginId: Long) =
        gymService.deleteGym(loginId)
}