package ru.lantt.moviescatalog.data.datasource

import ru.lantt.moviescatalog.domain.entity.Genre
import ru.lantt.moviescatalog.domain.entity.Review
import ru.lantt.moviescatalog.domain.entity.UserShort
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieDetailsContent

object MockDetailedMovieSource {

    val movie = MovieDetailsContent(
        time = 142,
        tagline = "Страх - это кандалы. Надежда - это свобода",
        description = "Несправедливо осужденный банкир готовит побег из тюрьмы. Тим Роббинс в выдающейся экранизации Стивена Кинга Несправедливо осужденный банкир готовит побег из тюрьмы. Тим Роббинс в выдающейся экранизации Стивена Кинга Несправедливо осужденный банкир готовит побег из тюрьмы. Тим Роббинс в выдающейся экранизации Стивена Кинга Несправедливо осужденный банкир готовит побег из тюрьмы. Тим Роббинс в выдающейся экранизации Стивена Кинга Несправедливо осужденный банкир готовит побег из тюрьмы. Тим Роббинс в выдающейся экранизации Стивена Кинга Несправедливо осужденный банкир готовит побег из тюрьмы. Тим Роббинс в выдающейся экранизации Стивена Кинга",
        director = "Фрэнк Дарабонт",
        budget = 25000000,
        fees = 28418687,
        ageLimit = 16,
        myReview = Review(
            reviewText = "«Побег из Шоушенка» - экранизация романа Стивена Кинга. На мой взгляд, это редчайший случай, когда фильм превзошел литературное произведение, по которому он был создан. И, несмотря на то, что ставить картину по книге всегда сложно, это тот пример, когда фильм не только прекрасно передал на экране всю глубину, атмосферу и смысловой стержень замечательной книги Кинга, но и оказался сильнее, превзойдя оригинал.",
            isAnonymous = true,
            createDateTime = "2021-12-08T02:38:31.3339317",
            author = null,
            id = "2d8d272d-487d-436a-5ee4-08d9b9f3d2b4",
            rating = 9
        ),
        usersReviews = listOf(
            Review(
                reviewText = "«Побег из Шоушенка» - экранизация романа Стивена Кинга. На мой взгляд, это редчайший случай, когда фильм превзошел литературное произведение, по которому он был создан. И, несмотря на то, что ставить картину по книге всегда сложно, это тот пример, когда фильм не только прекрасно передал на экране всю глубину, атмосферу и смысловой стержень замечательной книги Кинга, но и оказался сильнее, превзойдя оригинал.",
                isAnonymous = true,
                createDateTime = "2021-12-08T02:38:31.3339317",
                author = null,
                id = "2d8d272d-487d-436a-5ee4-08d9b9f3d2b4",
                rating = 9
            ),
            Review(
                reviewText = "Сразу скажу, что фильм мне понравился. Люблю Фримэна, уважаю Роббинса. Читаю Кинга. Но рецензия красненькая. Объясняю: В списке IMDB (как и на Кинопоиске) часто плавают фильмы, иногда вылезая и на второе место в списке, а потом опускаясь на сто девяносто третье, и т.д. Картина меняется. Но неизменно одно: 'Побег из Шоушенка' на первом месте. На сайте есть практически вся фильмография человечества, но лучшей остается эта история. Да, история неплохая, как и постановка фильма. Но разве это лучшее, что создал мир за более чем столетнюю работу? Это - то, что волнует людей? Самое насущное? Более глубокое, чем 'Форрест Гамп'? Более классическое, чем 'Титаник', негласно объявленный must see?",
                isAnonymous = false,
                createDateTime = "2021-12-08T02:38:31.3339306",
                author = UserShort(
                    userId = "2dff1d6f-ef1a-43de-706a-08d9b9f3d249",
                    nickName = "ivanov_ii",
                    avatar = null
                ),
                id = "b0238875-cab4-47f2-5ee5-08d9b9f3d2b4",
                rating = 3
            )
        ),
        id = "22158c42-001a-40a3-a2a7-08d9b9f3d2a2",
        name = "Побег из Шоушенка",
        poster = "https://kinopoisk-ru.clstorage.net/2s89R4K04/ae7af5CslCkK/rVSfH-4xE1g5HfOmBLXTnYX760KasO5cqWbK3B1pm2sMSA_4-9cDU2J9hILb0A-BVS95Ntv10PVvn9SkbRDtKtkRyu7P9EZd2Hlj1oGFojwQHioTLjWKHXhTH2nvmpnoLt8tmwlVAZV2GaBjrmW65VEvrNKLZBHBGLxltulXGEjeZQyN6LKjW4zc8XW0pBFpNYkpdOlDvJgpZVmeXujK-72Nrhm51yNFF5qQRX7HmmajT54ziwJAkeudULbYRykJ3uRdDiqxUWkt3rKUBATiuic4-Tbr4O6LaRbofZ14aIguX5_oexViJqbqAQfuVmglAs7cIvmCIeKrvXPXWHZq6I6kfAxZ8GF5Ta9SNjXlQ5hGKThGiEOfiRmk6M9uOykYjj7Nurj30UbEeGBl_CeqtgM9DuLrMdPDat0AFGrFSzruxM9c6INBae5_Mva0pZL5BzvahHvDrJiJdkl-DWhqeqw-_Urr9CPnxvtDty20e6bQjF5TWyICgJocwMT7R3rYjObPf5uAwenujHAX9gbCuBU5a1SbUy5pKRVKvJ6oC7t8rHzraVYTF0Xbk6cd97qncl_egKnwgkNLHoDFWqZaesy2Dqw4o1C7zpzQt_W18zuHiYrVC9LfqytnKsxe6gkY_s4t6xhnEyfWC-GFr7f7pwGszTPZIlFg2W4Rdyn3aAgcVg4c-cJTm05tQyXUloCrhUlI9unQT1toROsOPQoYuh_MHSirReIWF0jCt4_2iJVjb3yQ6TLAk8hd4MRrVSqoHEYPrnrhM6pvbNLUt4SDCzQ5GYdIc487aXUqzrx5motcPb8qq5eT9vRok2bvh_uVUqxtEymhsoJZvxAUKpSKqk8VvC9IUyBpbU1Q9_TX8Dg0OZinCGGem2inSb4euGrpTL7MKgpFMkbH--ElXOV6FtMv3CIp8pHga-4S5IsU2Jpshtzc63FBif1eE8XWZLFqVEuZJXvznYhJxLreXNvIu34Ovlhbl0Bm91iTl-yEuzfTDU5yGPPAIgndo0fZBMianXUtXoiQ07itztC3ZkTgqvcqOqbIgD0Km-XKrG1ZS2kOj_7ImNXjxseKQwV9pcj2kRx8EQpQY4Fqf3KGytYbO9yknWwas3B7_rwjxJYVU3lGS3pEmhLvCzlnCl1s65lrfB_tGFunMyXEWvOW3pe4VTJtr1EbYtOQq40iNRh0KXr9Bg0cGDDB6a5-szRlhtG6JknpBsrQn7g7xBufvXp6u19dfSgJZ-Ck5hgAZx_2K3dg3j6xeRIjYsnNQsca13iqjoX-zZgTU1u_nXI0BYfTi5Xam6dp0P96ObUKjcwbi6jv7d9KSmRQdzT4E9Qutasm8t_cEDswMELK33MGe-TIuR60Xp1KgZC4XRxA97Wk4ijlWik1CIHMadhUuv6MeYvpT4z9WrpXQhdlGKO1zDeKpKIO3VE7QiACW51gpzrHaMhdhs9sOGOwC12ccMdExfCpJcn5lXtAbkhYtgrsnip62B3e_irbJ8KX1Dpjdl-WSQezXizB6aDjcXldU5QKNykK_DY8fUgS8AhPHJGXtLehulabeQcqgu-ZKUbqzo7r2puef94LO6RDJ3ZJwTQ8tIg3MxzcsPiwImPLjMLFKDV7Ge9nnIzIIQP4Pd9DZXWX8Os3u1iG2oHfCGkFWVwe2nnKLK5fqQnl0kd2KAPW7sWYFfDsThPpkLLDOA7SNxjkKFmsd7zf-1PieS6vcRd3poC4VBq5FUohLZoLVph-X8hKW6xeXai7BgGlVymAdV60qhbgrf_haRHgkhu-ApX5V2rrH5RsH0gxcCptPtA3t8fRajZbCJTrwBy7W8bZDGxJe5r-Po1LOteDFuZoQGZNtTsHwD5OgwtwwjKpPqEnyTaqSZ4FnF3ZUpC4nt0hhpSF0XkVaxoVy-BMmQhEeO9Me9h5vAxsKJmH4Ja2apHUD7U5hVNN32P54eHC2n3Td1q0-PguVD1sqUHjqj6_4xYld_KLhVlq1-vxv4qJJ1jO3_lbW0_Nn3urNyOGp9ixV65luGQgvQwRe1BSULoPwZXK9Xh4rmStHlnhc6uMv0OUJiXC-GXKGWX6YK_6SrQqTE06C8kcba466wQAB_eZcIZMxFp04h2-sNnzYkKq7eC1aNUrO9z27T57wSC4b1yDh5TE8HtGS2onGVHOCOqUK178-hmZXI3uOgrmczemOrF0zYVK1jEtjGPowVFBWy8DF_rlqBjsVP1-CrDQGz3fIKZWZoDJRDvq1Wnznfg5ZEp8b0h4aZ4tX0jrtgKVBxlzJ94FaPWwDc1Ty0DgElu88SRqdVkYjQcPHfqjkws_L8KnZBcCmmdo-BY5kF4a2qQI3PzpKzsOvq34SlVCh1U4EFdulFq3Y34MgRkSYpPpXgPXGzZ6mn0nvH2LQtHrzZxS52WGEpsmC3omipGMa2r0SB-u21tKf2y_6ytVIZQFiXNFfBaLBeM_P8CI4KCya-wDFWqlaEqsZ828m0BDWI6fIdZmRAKaBbrrlQiADnk4dTkOvguIqu6Mfkp5ZjA2FQoTxe2USEayzk9AuzITQGgPg1SalijYDkf8rEuQ4Lov7KKl1mfziSZL2EaqId5q6Na6rKy5eGsefi_aaPfz5Per8wWedmj04N9tUsiAwAKJXtE3aCbKi9zEDH4Zw-O6HGyxFdbVI0kESPrVCBH_u1kWKo3cu4h63Y-9elpGQqQW-LH3LfVpFSJNPxELQNCDaz_zlzj3WKjs5h1dSrOj-H0OIIR2BbKJ1zoqd-liT2tpFss9r8moaJ4__HkrBZMmt8tz9D4Uu4XjfH4hOTGg8jvdM5bb9ysZ7Pa83crjYft9LjLU1rVDWTV7OjVJ45_LWycKrp8LSMsvzK7qe3WB1wWZghTf1Rj0os9NYqqTYGIKL_D02KRIqayXzq37orBKDpzRNFXUsnj1OXo1yOPv6NrmCN3Oe7jJP5yfuUhlwHXFm0OkbkYphDGvTxNK85Fyc#DSD",
        year = 1994,
        country = "США",
        genres = listOf(
            Genre(
                id = "9bba1883-a1fc-448a-aedb-08d9b9f3d27c",
                name = "драма"
            ),
            Genre(
                id = "9bba1883-a1fc-448a-aedb-08d9b9f3d27c",
                name = "драма"
            ),
            Genre(
                id = "9bba1883-a1fc-448a-aedb-08d9b9f3d27c",
                name = "драма"
            ),
            Genre(
                id = "9bba1883-a1fc-448a-aedb-08d9b9f3d27c",
                name = "драма"
            ),
            Genre(
                id = "9bba1883-a1fc-448a-aedb-08d9b9f3d27c",
                name = "драма"
            ),
            Genre(
                id = "9bba1883-a1fc-448a-aedb-08d9b9f3d27c",
                name = "драма"
            )
        ),
        rating = 6.3,
        isInFavorites = false
    )

}