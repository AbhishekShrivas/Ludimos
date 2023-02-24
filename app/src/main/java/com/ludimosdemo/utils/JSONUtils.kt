package com.ludimosdemo.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.*


class JSONUtils {
    companion object{
        fun getJSONFileString(context: Context): String {
            val `is`: InputStream =
                context.resources.openRawResource(com.ludimosdemo.R.raw.ball_track)
            val writer: Writer = StringWriter()
            val buffer = CharArray(1024)
            try {
                val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
                var n: Int
                while (reader.read(buffer).also { n = it } != -1) {
                    writer.write(buffer, 0, n)
                }
            } finally {
                `is`.close()
            }

            return writer.toString();
        }

    }
}