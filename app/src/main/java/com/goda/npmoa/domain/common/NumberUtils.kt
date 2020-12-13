package com.goda.npmoa.domain.common


fun Float.formatTo(numberOfDecimals: Int) =
        String.format("%.${numberOfDecimals}f", this)