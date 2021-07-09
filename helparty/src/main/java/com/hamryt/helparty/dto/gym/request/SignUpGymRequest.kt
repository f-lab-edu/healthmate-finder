package com.hamryt.helparty.dto.gym.request

import com.hamryt.helparty.dto.UserType
import javax.validation.constraints.NotEmpty

data class SignUpGymRequest(
    val email: @NotEmpty String,
    val gymName: @NotEmpty String,
    val password: @NotEmpty String,
    val phoneNumber: @NotEmpty String,
    val addressCode: @NotEmpty String,
    val addressDetail: @NotEmpty String,
    val userType: UserType

) {
    companion object {
        @JvmStatic
        fun create(
            email: @NotEmpty String,
            gymName: @NotEmpty String,
            password: @NotEmpty String,
            phoneNumber: @NotEmpty String,
            addressCode: @NotEmpty String,
            addressDetail: @NotEmpty String,
            userType: UserType
        ) =
            SignUpGymRequest(email, gymName, password, phoneNumber, addressCode, addressDetail, userType)
    }
}
