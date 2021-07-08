package com.hamryt.helparty.dto.gym.request

import com.hamryt.helparty.dto.UserType
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class SignUpGymRequest(
        var email: @NotEmpty String,
        var gymName: @NotEmpty String,
        var password: @NotEmpty String,
        var phoneNumber: @NotEmpty String,
        var addressCode: @NotEmpty String,
        var addressDetail: @NotEmpty String,
        var userType: @NotNull UserType

) {
    companion object {
        @JvmStatic
        fun create(email: @NotEmpty String, gymName: @NotEmpty String, password: @NotEmpty String,
                   phoneNumber: @NotEmpty String, addressCode: @NotEmpty String, addressDetail: @NotEmpty String,
                   userType: @NotNull UserType) =
                SignUpGymRequest(email, gymName, password, phoneNumber, addressCode, addressDetail, userType)
    }
}