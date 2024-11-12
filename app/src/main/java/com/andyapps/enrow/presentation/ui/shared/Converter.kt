package com.andyapps.enrow.presentation.ui.shared

import com.andyapps.enrow.domain.valueobject.CheckInDaysSet
import com.andyapps.enrow.presentation.model.UiDay

object DomainHelper {
    fun convertToCheckInDays(checkInDays: Set<SelectItem<UiDay>>) : String {
        return checkInDays.joinToString("") {
            if (it.isSelected)
                CheckInDaysSet.DAY_IS_SELECTED.toString()
            else
                CheckInDaysSet.DAY_IS_NOT_SELECTED.toString()
        }
    }
}