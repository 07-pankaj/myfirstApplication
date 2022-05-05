package com.example.myfirstapplication.children

import android.os.Parcelable
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "childrenData_table")
@Parcelize
data class ChildrenData(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "childName")val childName: String,
    @ColumnInfo(name = "childAge")val childAge: String,
    @ColumnInfo(name = "childMangeOption")val childManageOption: String,
    @ColumnInfo(name = "ChildImage")val childImageUrl: String?
) : Parcelable {

}
