package br.com.paulosalvatore.pagseguroandroidturmaa.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.paulosalvatore.pagseguroandroidturmaa.dao.WordDao
import br.com.paulosalvatore.pagseguroandroidturmaa.entities.Word
import org.jetbrains.anko.doAsync

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private var instance: WordRoomDatabase? = null

        private val roomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                instance?.let {
                    doAsync {
                        val dao = it.wordDao()

                        dao.deleteAll()

                        val word1 = Word("PagSeguro")
                        dao.insert(word1)

                        val word2 = Word("Kotlin Android")
                        dao.insert(word2)
                    }
                }
            }
        }

        fun getDatabase(context: Context): WordRoomDatabase {
            if (instance == null) {
                synchronized(WordRoomDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    )
//                        .addCallback(roomDatabaseCallback)
                        .build()
                }
            }

            return instance!!
        }
    }
}
