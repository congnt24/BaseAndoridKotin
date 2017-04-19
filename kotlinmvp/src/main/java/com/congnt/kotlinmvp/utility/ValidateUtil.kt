package com.congnt.kotlinmvp.utility

import java.util.regex.Pattern

var PATTERN_MOBILE_REGEX = Pattern.compile("0\\d{9,10}")
var PATTERN_EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
//val PATTERN_PASSWORD_REGEX2 = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\s+$).{5,10}")
val PATTERN_PASSWORD_REGEX2 = Pattern.compile("(?=.*[0-9])(?=.*[a-zA-Z]).{8,}")
val PATTERN_MONTH_CARD_REGEX = Pattern.compile("(^0[1-9]){1,2}$|(^1[0-2]){1,2}$")
val PATTERN_CCV_CARD_REGEX_3 = Pattern.compile("^[0-9]{3}$")
val PATTERN_CCV_CARD_REGEX_4 = Pattern.compile("^[0-9]{4}$")
val PATTERN_CARD_NUMBER_REGEX = Pattern.compile("^[3-5][0-9]{9,}$")
val PATTERN_CHECK_EMAIL_REX = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+")
var PATTERN_NAME_PERSON_REX = Pattern.compile("[\\p{L}- ]+")
var PATTERN_NUMBER_PHONE_REX = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?"
        + "(\\([0-9]+\\)[\\- \\.]*)?"
        + "([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])")
var PATTERN_ALPHALT_REX = Pattern.compile("[A-z\u00C0-\u00ff \\./-]*")