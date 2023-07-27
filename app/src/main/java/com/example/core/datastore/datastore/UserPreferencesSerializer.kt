package com.example.core.datastore.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.UserPreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject


class UserPreferencesSerializer @Inject constructor() : Serializer<UserPreferences> {
    override val defaultValue = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences =
        try {
            @Suppress("BlockingMethodInNonBlockingContext")
            UserPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        @Suppress("BlockingMethodInNonBlockingContext")
        t.writeTo(output)
    }
}