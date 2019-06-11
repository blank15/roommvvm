package id.yudistiro.viewmodel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class],version = 1)
public abstract class WordRoomDatabase :RoomDatabase(){
        abstract fun wordDAO():WordDao



    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope) : WordRoomDatabase{
           val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                return Room.databaseBuilder(context.applicationContext,
                        WordRoomDatabase::class.java,
                    "Word_database"
                    )
                    .build()
            }

        }
    }

}