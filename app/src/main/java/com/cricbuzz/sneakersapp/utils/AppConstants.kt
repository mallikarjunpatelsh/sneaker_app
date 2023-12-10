package com.cricbuzz.sneakersapp.utils

/**
 * App constants
 * Sealed class which have the constants needed in project
 * @constructor Create empty App constants
 */
sealed class AppConstants {
    /**
     * Network constants
     * @constructor Create empty Network constants
     */
    class NetworkConstants: AppConstants() {
        companion object {
            const val DEFAULT_ERROR_CODE = -2
            const val INTERNET_ERROR_CODE = -1
        }
    }

    /**
     * Database related constants
     * @constructor Create empty D b constants
     */
    class DBConstants: AppConstants() {
        companion object {
            const val DB_NAME = "s_database"
            const val PRODUCT_ENTITY = "product"
        }
    }
}
