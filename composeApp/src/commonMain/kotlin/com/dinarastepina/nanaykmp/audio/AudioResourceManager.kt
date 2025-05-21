package com.dinarastepina.nanaykmp.audio

import nanaykmp.composeapp.generated.resources.Res

class AudioResourceManager {
    suspend fun getAudioBytes(path: String): ByteArray {
        return Res.readBytes("files/audio/$path")
    }
}