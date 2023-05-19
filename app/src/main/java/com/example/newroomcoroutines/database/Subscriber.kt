package com.example.newroomcoroutines.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_data")
data class Subscriber (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("subscriber_id")
    var id: Int,
    @ColumnInfo("subscriber_name")
    var name: String,
    @ColumnInfo("subscriber_email")
    var email: String
)
