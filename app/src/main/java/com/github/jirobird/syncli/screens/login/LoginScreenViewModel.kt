package com.github.jirobird.syncli.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.jirobird.syncli.common.RequestResponse
import com.github.jirobird.syncli.domain.use_cases.partner.GetHasPartnerWithSameNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val getHasPartnerWithSameNameUseCase: GetHasPartnerWithSameNameUseCase
): ViewModel() {

    fun checkSamePartner(name:String) {
        getHasPartnerWithSameNameUseCase(name).onEach { result ->
            when(result ) {
                is RequestResponse.Success -> {
                    Log.d("","")
                }

                is RequestResponse.Error -> {
                    Log.d("","")
                }

                is RequestResponse.Loading -> {
                    Log.d("","")
                }
            }
        }.launchIn(viewModelScope)
    }
}