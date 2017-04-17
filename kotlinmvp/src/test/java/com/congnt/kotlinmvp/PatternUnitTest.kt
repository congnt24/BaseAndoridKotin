package com.congnt.kotlinmvp

import checkMatchPattern
import com.congnt.kotlinmvp.utility.PATTERN_EMAIL_REGEX
import com.congnt.kotlinmvp.utility.PATTERN_PASSWORD_REGEX
import com.congnt.kotlinmvp.utility.PATTERN_PASSWORD_REGEX2
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by congn on 4/17/2017.
 */
class PatternUnitTest {
    @Test
    fun testPatternEmail(){
        var email1 = "asjdasaaa@gmail.com"
        var email2 = "aaaaa@gmail.com"
        assertTrue(email1.checkMatchPattern(PATTERN_EMAIL_REGEX))
        assertTrue(email2.checkMatchPattern(PATTERN_EMAIL_REGEX))
    }
    @Test
    fun testPatternPassword(){
        var pass1 = "sfgassf435"
        var pass2 = "1234"
        assertTrue(pass1.checkMatchPattern(PATTERN_PASSWORD_REGEX2))
//        assertTrue(pass2.checkMatchPattern(PATTERN_PASSWORD_REGEX2)) false
    }
}