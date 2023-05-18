package com.giphyreactnativesdk


import android.content.Context
import com.facebook.react.bridge.ReadableMap
import com.giphy.sdk.ui.themes.GPHCustomTheme
import com.giphy.sdk.ui.themes.GPHTheme
import com.giphy.sdk.ui.themes.Theme
import com.giphyreactnativesdk.utils.CaseConverter

val DEFAULT_PRESET = GPHTheme.Dark


class RNGiphyTheme(context: Context, private var config: ReadableMap?) {
  private var preset: Theme

  init {
    val presetTheme = gphThemeByName(config?.getString("preset")) ?: DEFAULT_PRESET
    preset = presetTheme.getThemeResources(context)
  }

  fun applyToGPHCustomTheme() {
    GPHCustomTheme.apply {
      // region dialog's handle

      handleBarColor = getIntOrPreset("handleBarColor")

      // endregion

      // region emoji drawer

      emojiDrawerGradientTopColor = getIntOrPreset("emojiDrawerGradientTopColor")
      emojiDrawerGradientBottomColor = getIntOrPreset("emojiDrawerGradientBottomColor")
      emojiDrawerSeparatorColor = getIntOrPreset("emojiDrawerSeparatorColor")


      // endregion

      // region search bar

      searchBackButtonColor = getIntOrPreset("searchBackButtonColor")
      searchBarBackgroundColor = getIntOrPreset("searchBarBackgroundColor")
      searchTextColor = getIntOrPreset("searchTextColor")
      searchPlaceholderTextColor = getIntOrPreset("searchPlaceholderTextColor")

      // endregion

      // region suggestions

      suggestionCellBackgroundColor = getIntOrPreset("suggestionCellBackgroundColor")
      suggestionCellTextColor = getIntOrPreset("suggestionCellTextColor")

      // endregion

      // region tab bar

      tabBarSwitchDefaultColor = getIntOrPreset("tabBarSwitchDefaultColor")
      tabBarSwitchSelectedColor = getIntOrPreset("tabBarSwitchSelectedColor")

      // endregion

      // region confirmation

      confirmationSelectButtonColor = getIntOrPreset("confirmationSelectButtonColor")
      confirmationSelectButtonTextColor = getIntOrPreset("confirmationSelectButtonTextColor")
      confirmationBackButtonColor = getIntOrPreset("confirmationBackButtonColor")
      confirmationViewOnGiphyColor = getIntOrPreset("confirmationViewOnGiphyColor")

      // endregion

      // region other

      defaultTextColor = getIntOrPreset("defaultTextColor")
      backgroundColor = getIntOrPreset("backgroundColor")
      dialogOverlayBackgroundColor = getIntOrPreset("dialogOverlayBackgroundColor")
      usernameColor = getIntOrPreset("usernameColor")

      // endregion
    }
  }

  private fun getIntOrPreset(field: String): Int {
    if (config == null || config?.hasKey(field) == false) {
      return getPresetField(field) as Int
    }
    return config!!.getInt(field)
  }

  @Suppress("IMPLICIT_CAST_TO_ANY")
  private fun getPresetField(field: String): Any {
    return when (field) {
      "handleBarColor" -> preset.handleBarColor
      "emojiDrawerGradientTopColor" -> preset.emojiDrawerGradientTopColor
      "emojiDrawerGradientBottomColor" -> preset.emojiDrawerGradientBottomColor
      "emojiDrawerSeparatorColor" -> preset.emojiDrawerSeparatorColor
      "searchBarBackgroundColor" -> preset.searchBarBackgroundColor
      "searchTextColor" -> preset.searchTextColor
      "searchButtonIcon" -> preset.searchButtonIcon
      "searchBackButtonColor" -> preset.searchBackButtonColor
      "searchPlaceholderTextColor" -> preset.searchPlaceholderTextColor
      "suggestionCellBackgroundColor" -> preset.suggestionCellBackgroundColor
      "suggestionCellTextColor" -> preset.suggestionCellTextColor
      "tabBarSwitchDefaultColor" -> preset.tabBarSwitchDefaultColor
      "tabBarSwitchSelectedColor" -> preset.tabBarSwitchSelectedColor
      "confirmationSelectButtonColor" -> preset.confirmationSelectButtonColor
      "confirmationSelectButtonTextColor" -> preset.confirmationSelectButtonTextColor
      "confirmationBackButtonColor" -> preset.confirmationBackButtonColor
      "confirmationViewOnGiphyColor" -> preset.confirmationViewOnGiphyColor
      "backgroundColor" -> preset.backgroundColor
      "dialogOverlayBackgroundColor" -> preset.dialogOverlayBackgroundColor
      "defaultTextColor" -> preset.defaultTextColor
      "usernameColor" -> preset.usernameColor
      else -> {
        throw IllegalArgumentException("Unknown field: $field")
      }
    } as Any
  }

  companion object {
    private fun gphThemeByName(name: String?): GPHTheme? {
      if (name == null) {
        return null
      }
      return GPHTheme.values().firstOrNull {
        it.name == CaseConverter.capitalize(name)
      }
    }
  }
}
