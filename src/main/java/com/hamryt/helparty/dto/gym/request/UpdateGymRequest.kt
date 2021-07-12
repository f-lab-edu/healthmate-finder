package com.hamryt.helparty.dto.gym.request

import com.hamryt.helparty.dto.UserType
import javax.validation.constraints.NotEmpty

data class UpdateGymRequest(
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
            gymName: @NotEmpty String,
            password: @NotEmpty String,
            phoneNumber: @NotEmpty String,
            addressCode: @NotEmpty String,
            addressDetail: @NotEmpty String,
            userType: UserType
        ) =
            UpdateGymRequest(gymName, password, phoneNumber, addressCode, addressDetail, userType)
    }
}
