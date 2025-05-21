package com.dinarastepina.nanaykmp.audio

class PlaylistManager(private val audioPlayerFactory: AudioPlayerFactory) {
    private val player: AudioPlayer = audioPlayerFactory.createAudioPlayer()

    fun playTrack(track: AudioTrack) {
        stop()
        player.setDataSource(track.path)
        player.play()
    }

    fun pause() {
        player.pause()
    }

    fun stop() {
        player.stop()
    }

    fun release() {
        player.release()
    }

    fun isPlaying(): Boolean = player.isPlaying()
}