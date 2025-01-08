package ru.lantt.moviescatalog.domain.usecase

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import ru.lantt.moviescatalog.domain.entity.ValidationErrorType

@RunWith(RobolectricTestRunner::class)
class ValidateUrlUseCaseTest {

    private val useCase = ValidateUrlUseCase()

    @Test
    fun `get empty url returns EMPTY_FIELD error`() {
        // given
        val url = ""
        val expected = ValidationErrorType.EMPTY_FIELD

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get valid url with ru domain returns null`() {
        // given
        val url = "https://www.example.ru"
        val expected = null

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get valid url with com domain returns null`() {
        // given
        val url = "https://www.example.com"
        val expected = null

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get url without https returns INVALID_URL error`() {
        // given
        val url = "www.example.com"
        val expected = ValidationErrorType.INVALID_URL

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get url with no slashes returns INVALID_URL error`() {
        // given
        val url = "https:www.example.com"
        val expected = ValidationErrorType.INVALID_URL

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get url with invalid protocol returns INVALID_URL error`() {
        // given
        val url = "ftp://example.com"
        val expected = ValidationErrorType.INVALID_URL

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get url with domain only returns INVALID_URL error`() {
        // given
        val url = "example"
        val expected = ValidationErrorType.INVALID_URL

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get url with incorrect protocol domain returns INVALID_URL error`() {
        // given
        val url = "htps://example.com"
        val expected = ValidationErrorType.INVALID_URL

        // when
        val actual = useCase.invoke(url)

        // then
        Assert.assertEquals(expected, actual)
    }
}