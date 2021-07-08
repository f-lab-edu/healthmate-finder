package com.hamryt.helparty.dto.gym

import com.hamryt.helparty.dto.UserType

data class GymDTO(
        var id: Long?,
        var email: String,
        var gymName: String,
        var password: String,
        var phoneNumber: String,
        var addressCode: String,
        var addressDetail: String,
        var userType: UserType
) {
    companion object {
        @JvmStatic
        fun create(id: Long?, email: String, gymName: String,
                   password: String, phoneNumber: String, addressCode: String,
                   addressDetail: String, userType: UserType) =
                GymDTO(id, email, gymName, password, phoneNumber, addressCode, addressDetail, userType)

    }
}
