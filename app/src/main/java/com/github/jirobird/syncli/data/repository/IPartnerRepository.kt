package com.github.jirobird.syncli.data.repository

import com.github.jirobird.syncli.data.remote.dto.partner.PartnerDto


interface IPartnerRepository {

    suspend fun hasPartnerWithSameName(name: String):Boolean

    suspend fun findPartnerInformationByName(name: String): PartnerDto
}