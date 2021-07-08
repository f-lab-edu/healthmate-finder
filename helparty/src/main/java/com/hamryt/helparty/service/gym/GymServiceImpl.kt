package com.hamryt.helparty.service.gym

import com.hamryt.helparty.dto.gym.GymDTO
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse
import com.hamryt.helparty.exception.gym.GymDeleteFailedException
import com.hamryt.helparty.exception.gym.GymNotFoundException
import com.hamryt.helparty.exception.gym.InsertGymFailedException
import com.hamryt.helparty.exception.user.EmailExistedException
import com.hamryt.helparty.exception.user.UpdateFailedException
import com.hamryt.helparty.mapper.GymMapper
import com.hamryt.helparty.service.session.Encryptor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.validation.constraints.NotEmpty

@Service
class GymServiceImpl(

        private val encryptor: Encryptor,

        private val gymMapper: GymMapper

) : GymService {

    override fun insertGym(resource: SignUpGymRequest): SignUpGymResponse {
        resource.userType.validEqualUserType("GYM")

        if(isExistsEmail(resource.email)){
            throw EmailExistedException(resource.email)
        }

        var encodedpassword = encryptor.encrypt(resource.password)

        var newGym = GymDTO.create(
                null,
                resource.email,
                resource.gymName,
                encodedpassword,
                resource.phoneNumber,
                resource.addressCode,
                resource.addressDetail,
                resource.userType
        )

        if(gymMapper.insertGym(newGym) != 1){throw InsertGymFailedException()}

        return SignUpGymResponse.of(newGym)
    }

    @Transactional(readOnly = true)
    override fun findGymByEmailAndPassword(email: String, password: String): GymDTO {
        return gymMapper.findGymByEmailAndPassword(email, password)?: throw GymNotFoundException(email)
    }

    override fun updateGym(loginId: Long, updateGymRequest: UpdateGymRequest): UpdateGymResponse {
        var encodedPassword = encryptor.encrypt(updateGymRequest.password)

        var updateGymResponse = UpdateGymResponse.of(loginId, encodedPassword, updateGymRequest)

        if(gymMapper.updateGym(updateGymResponse)!=1){
            throw UpdateFailedException(loginId);
        }

        return updateGymResponse
    }

    override fun deleteGym(loginId: Long) {
        if(gymMapper.deleteGymById(loginId) != 1){
            throw GymDeleteFailedException(loginId)
        }
    }

    @Transactional(readOnly = true)
    private fun isExistsEmail(email: @NotEmpty String) = gymMapper.isExistsEmail(email)

}