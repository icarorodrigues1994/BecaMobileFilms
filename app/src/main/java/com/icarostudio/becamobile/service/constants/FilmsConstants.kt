package com.icarostudio.becamobile.service.constants

class FilmsConstants {

    object HTTP {
        const val SUCCESS = 200
        const val SUCCESS_CREATED = 201
    }

    object QUERY {
        const val API_KEY = "2b9ab4997f09a5a4e0c01dbc9e6456cc"
        const val SESSIO_ID = "5e7a2fc766d4bb85a38749c1ef8b85e294406b54"
        const val VIDEOS = "videos"
    }

    object REQUEST{
        const val IMAGE_W185 = "https://image.tmdb.org/t/p/w185"
        const val IMAGE_W500 = "https://image.tmdb.org/t/p/w500"
        const val IMAGE_W780 = "https://image.tmdb.org/t/p/w780"
        const val IMAGE_ORIGINAL = "https://image.tmdb.org/t/p/original"
        const val IMAGE_W1280 = "https://image.tmdb.org/t/p/w1280"
    }

    object BUNDLE {
        const val TVID = "tvid"
        const val FILMID = "filmid"
        const val FILMFILTER = "filmfilter"
    }

    object TYPE {
        const val MOVIE = "movie"
        const val TV = "tv"
        const val STATUS_DELETE = "The item/record was deleted successfully."
    }


}