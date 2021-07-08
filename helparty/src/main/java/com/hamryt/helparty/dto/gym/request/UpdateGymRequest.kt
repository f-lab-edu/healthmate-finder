package com.hamryt.helparty.dto.gym.request

import com.hamryt.helparty.dto.UserType
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UpdateGymRequest(
        var gymName: @NotEmpty String,
        var password: @NotEmpty String,
        var phoneNumber: @NotEmpty String,
        var addressCode: @NotEmpty String,
        var addressDetail: @NotEmpty String,
        var userType: @NotNull UserType
) {
    companion object {
        @JvmStatic
        fun create(gymName: @NotEmpty String, password: @NotEmpty String, phoneNumber: @NotEmpty String,
                   addressCode: @NotEmpty String, addressDetail: @NotEmpty String, userType: @NotNull UserType) =
                UpdateGymRequest(gymName, password, phoneNumber, addressCode, addressDetail, userType)
    }
}
