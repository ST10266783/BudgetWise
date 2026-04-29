package com.budgetwise.app

import com.budgetwise.app.utils.DateUtils
import com.budgetwise.app.utils.hashPassword
import com.budgetwise.app.utils.isPasswordValid
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Unit tests for utility functions and validation logic.
 * These tests run on the JVM with no Android dependencies.
 */
@RunWith(JUnit4::class)
class UtilsTest {

    // ── Password validation ──────────────────────────────────────────────────

    @Test
    fun `valid password passes all checks`() {
        assertTrue(isPasswordValid("Password1"))
    }

    @Test
    fun `password too short fails`() {
        assertFalse(isPasswordValid("Pass1"))
    }

    @Test
    fun `password without uppercase fails`() {
        assertFalse(isPasswordValid("password1"))
    }

    @Test
    fun `password without digit fails`() {
        assertFalse(isPasswordValid("Password"))
    }

    @Test
    fun `empty password fails`() {
        assertFalse(isPasswordValid(""))
    }

    // ── Password hashing ─────────────────────────────────────────────────────

    @Test
    fun `same password produces same hash`() {
        assertEquals(hashPassword("Secret1!"), hashPassword("Secret1!"))
    }

    @Test
    fun `different passwords produce different hashes`() {
        assertNotEquals(hashPassword("Password1"), hashPassword("Password2"))
    }

    @Test
    fun `hash is 64 hex characters (SHA-256)`() {
        val hash = hashPassword("Password1")
        assertEquals(64, hash.length)
        assertTrue(hash.all { it.isLetterOrDigit() })
    }

    // ── Date validation ──────────────────────────────────────────────────────

    @Test
    fun `valid date passes`() {
        assertTrue(DateUtils.isValidDate("2026-04-15"))
    }

    @Test
    fun `invalid date fails`() {
        assertFalse(DateUtils.isValidDate("not-a-date"))
    }

    @Test
    fun `impossible date fails`() {
        assertFalse(DateUtils.isValidDate("2026-13-45"))
    }

    @Test
    fun `empty date fails`() {
        assertFalse(DateUtils.isValidDate(""))
    }

    // ── Time validation ──────────────────────────────────────────────────────

    @Test
    fun `valid time 09 colon 00 passes`() {
        assertTrue(DateUtils.isValidTime("09:00"))
    }

    @Test
    fun `valid time 23 colon 59 passes`() {
        assertTrue(DateUtils.isValidTime("23:59"))
    }

    @Test
    fun `invalid hour 25 fails`() {
        assertFalse(DateUtils.isValidTime("25:00"))
    }

    @Test
    fun `invalid minute 61 fails`() {
        assertFalse(DateUtils.isValidTime("10:61"))
    }

    @Test
    fun `empty time fails`() {
        assertFalse(DateUtils.isValidTime(""))
    }

    // ── Date utility functions ───────────────────────────────────────────────

    @Test
    fun `today returns correctly formatted date`() {
        val today = DateUtils.today()
        assertTrue("Date should match yyyy-MM-dd", today.matches(Regex("\\d{4}-\\d{2}-\\d{2}")))
    }

    @Test
    fun `currentMonth returns correctly formatted month`() {
        val month = DateUtils.currentMonth()
        assertTrue("Month should match yyyy-MM", month.matches(Regex("\\d{4}-\\d{2}")))
    }

    @Test
    fun `startOfMonth day is 01`() {
        val start = DateUtils.startOfCurrentMonth()
        assertTrue(start.endsWith("-01"))
    }

    @Test
    fun `startOfMonth is before or equal to endOfMonth`() {
        val start = DateUtils.startOfCurrentMonth()
        val end   = DateUtils.endOfCurrentMonth()
        assertTrue(start <= end)
    }
}
