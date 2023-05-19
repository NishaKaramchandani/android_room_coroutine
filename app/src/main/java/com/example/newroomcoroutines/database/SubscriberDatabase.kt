package com.example.newroomcoroutines.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    companion object {
        @Volatile
        private var INSTANCE : SubscriberDatabase? = null
            fun getInstance(context: Context): SubscriberDatabase {
                synchronized(this) {
                    var instance = INSTANCE
                    if(instance == null) {
                        instance = Room.databaseBuilder(
                            context = context.applicationContext,
                            SubscriberDatabase::class.java,
                            name = "subscriber_database"
                        ).build()
                        INSTANCE = instance
                    }
                    return instance
                }

            }
    }

}
