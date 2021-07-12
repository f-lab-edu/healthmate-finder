package com.hamryt.helparty.dto.gym

import com.hamryt.helparty.dto.UserType

data class GymDTO(
    val id: Long?,
    val email: String,
    val gymName: String,
    val password: String,
    val phoneNumber: String,
    val addressCode: String,
    val addressDetail: String,
    val userType: UserType
) {
    companion object {
        @JvmStatic
        fun create(
            id: Long?, email: String, gymName: String,
            password: String, phoneNumber: String, addressCode: String,
            addressDetail: String, userType: UserType
        ) =
            GymDTO(id, email, gymName, password, phoneNumber, addressCode, addressDetail, userType)

    }
}
