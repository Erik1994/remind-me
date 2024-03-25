package com.compose.project.remindme.presentation.biometric

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import com.compose.project.remindme.R
import com.compose.project.remindme.core.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class BiometricManager(
    private val activity: AppCompatActivity
) {
    private val resultChannel = Channel<BiometricResult?>()
    val promptResult = resultChannel.receiveAsFlow()

    fun showBiometric(
        title: String,
        description: String
    ) {
        val manager = BiometricManager.from(activity)
        val authenticators = if (Build.VERSION.SDK_INT >= 30) {
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        } else BIOMETRIC_STRONG
        val promptInfo =
            PromptInfo.Builder()
                .setTitle(title)
                .setDescription(description)
                .setAllowedAuthenticators(authenticators)
        if (Build.VERSION.SDK_INT < 30) {
            promptInfo.setNegativeButtonText("Cancel")
        }
        when (manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                resultChannel.trySend(
                    BiometricResult.HardwareUnavailable
                )
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultChannel.trySend(
                    BiometricResult.FeatureUnavailable
                )
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                resultChannel.trySend(
                    BiometricResult.AuthenticationNotSet
                )
                return
            }
            else -> Unit
        }

        val prompt = BiometricPrompt(
            activity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    resultChannel.trySend(
                        BiometricResult.AuthenticationError(errString.toString())
                    )
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    resultChannel.trySend(
                        BiometricResult.AuthenticationSuccess
                    )
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    resultChannel.trySend(
                        BiometricResult.AuthenticationFailed
                    )
                }
            }
        )
        prompt.authenticate(promptInfo.build())
    }

    fun resetState() {
        resultChannel.trySend(null)
    }

    sealed class BiometricResult(val resulMessage: UiText) {
        object HardwareUnavailable :
            BiometricResult(UiText.StringResource(R.string.hardware_unavailable))

        object FeatureUnavailable :
            BiometricResult(UiText.StringResource(R.string.feature_unavailable))

        object AuthenticationFailed :
            BiometricResult(UiText.StringResource(R.string.authentication_failed))

        object AuthenticationSuccess :
            BiometricResult(UiText.StringResource(R.string.authentication_succeeded))

        object AuthenticationNotSet :
            BiometricResult(UiText.StringResource(R.string.authentication_not_set))

        data class AuthenticationError(val error: String) :
            BiometricResult(UiText.DynamicString(error))
    }
}