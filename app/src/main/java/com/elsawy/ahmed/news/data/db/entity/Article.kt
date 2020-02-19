package com.elsawy.ahmed.news.data.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "article_table")
data class Article  (
    @Nullable
    @SerializedName("author")
    val author: String,
    @Nullable
    @SerializedName("content")
    val content: String,
    @Nullable
    @SerializedName("description")
    val description: String,
    @Nullable
    @SerializedName("publishedAt")
    val publishedAt: String,
    @Embedded(prefix = "source_")
    val source: Source,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("title")
    val title: String,
    @Nullable
    @SerializedName("url")
    val url: String,
    @Nullable
    @SerializedName("urlToImage")
    val urlToImage: String
): Parcelable {

//    @PrimaryKey(autoGenerate = true)
//    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(Source::class.java.classLoader)!!,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(description)
        parcel.writeString(publishedAt)
        parcel.writeParcelable(source, flags)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}
