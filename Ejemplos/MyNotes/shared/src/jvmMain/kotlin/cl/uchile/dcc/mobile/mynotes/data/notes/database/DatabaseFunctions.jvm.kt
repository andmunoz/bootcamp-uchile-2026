package cl.uchile.dcc.mobile.mynotes.data.notes.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = "notes.db"
        val databaseFile = File(databasePath)
        val databaseExists = databaseFile.exists()
        val driver = JdbcSqliteDriver("jdbc:sqlite:$databasePath")
        if (!databaseExists) {
            NoteDB.Schema.create(driver)
        }
        return driver
    }
}