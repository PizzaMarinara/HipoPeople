package dev.efantini.hipopeople.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.efantini.hipopeople.data.local.dao.MemberDao
import dev.efantini.hipopeople.domain.model.Member

@Database(
    entities = [Member::class],
    version = 1,
    exportSchema = false
)
abstract class HipoPeopleDatabase : RoomDatabase() {

    abstract val memberDao: MemberDao

    companion object {
        const val DATABASE_NAME = "hipo_db"
    }
}
