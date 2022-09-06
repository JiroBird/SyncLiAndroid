package com.github.jirobird.syncli.data.repository

import com.github.jirobird.syncli.data.remote.dto.partner.IPartnerApi
import com.github.jirobird.syncli.data.remote.dto.partner.IPartnerRepository
import com.github.jirobird.syncli.data.remote.dto.partner.PartnerDto
import javax.inject.Inject

class PartnerRepositoryImpl @Inject constructor(
    private val partnerApi: IPartnerApi
): IPartnerRepository {
    override suspend fun hasPartnerWithSameName(name: String): Boolean {
        return partnerApi.hasUserName(name)
    }

    override suspend fun findPartnerInformationByName(name: String): PartnerDto {
        return partnerApi.findPartnerInformationByName(name)
    }
}