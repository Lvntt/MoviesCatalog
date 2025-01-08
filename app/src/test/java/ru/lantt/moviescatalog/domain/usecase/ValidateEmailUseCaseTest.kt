package ru.lantt.moviescatalog.domain.usecase

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import ru.lantt.moviescatalog.domain.entity.ValidationErrorType

@RunWith(RobolectricTestRunner::class)
class ValidateEmailUseCaseTest {

    private val useCase = ValidateEmailUseCase()

    @Test
    fun `get empty email returns EMPTY_FIELD error`() {
        // given
        val email = ""
        val expected = ValidationErrorType.EMPTY_FIELD

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get valid email returns null`() {
        // given
        val email = "example@gmail.com"
        val expected = null

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get valid email with 1-symbol name returns null`() {
        // given
        val email = "e@gmail.com"
        val expected = null

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get email without at symbol returns INVALID_EMAIL error`() {
        // given
        val email = "examplegmail.com"
        val expected = ValidationErrorType.INVALID_EMAIL

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get email without domain returns INVALID_EMAIL error`() {
        // given
        val email = "example@"
        val expected = ValidationErrorType.INVALID_EMAIL

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get email with invalid characters returns INVALID_EMAIL error`() {
        // given
        val email = "example@gmail!.com"
        val expected = ValidationErrorType.INVALID_EMAIL

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get email without name returns INVALID_EMAIL error`() {
        // given
        val email = "@gmail.com"
        val expected = ValidationErrorType.INVALID_EMAIL

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get email with multiple at symbols returns INVALID_EMAIL error`() {
        // given
        val email = "example@@gmail.com"
        val expected = ValidationErrorType.INVALID_EMAIL

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get email with spaces returns INVALID_EMAIL error`() {
        // given
        val email = "example@ gmail.com"
        val expected = ValidationErrorType.INVALID_EMAIL

        // when
        val actual = useCase.invoke(email)

        // then
        Assert.assertEquals(expected, actual)
    }
}