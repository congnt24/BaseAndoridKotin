package com.congnt.kotlinmvp.utility

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build

import java.io.File

/**
 * Created by congnt24 on 24/09/2016.
 */

object SoundUtil {
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(context: Context, idRaw: Int) {
        mediaPlayer = MediaPlayer.create(context, idRaw)
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer!!.start()
    }

    fun playSoundRepeat(context: Context, idRaw: Int) {
        mediaPlayer = MediaPlayer.create(context, idRaw)
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer!!.isLooping = true
        mediaPlayer!!.start()
    }


    fun playSound(context: Context, path: String, listener: MediaPlayer.OnCompletionListener) {
        mediaPlayer = MediaPlayer.create(context, Uri.fromFile(File(path)))
        mediaPlayer!!.setOnCompletionListener(listener)
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer!!.start()
    }


    fun stop() {
        if (mediaPlayer != null) {
            mediaPlayer!!.isLooping = false
            mediaPlayer!!.reset()
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    fun setStreamMute(context: Context, isMute: Boolean) {
        val mAudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //            if (!mAudioManager.isStreamMute(AudioManager.STREAM_MUSIC)) {
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, if (isMute) AudioManager.ADJUST_MUTE else AudioManager.ADJUST_UNMUTE, AudioManager.ADJUST_SAME)
            //            }
        } else {
            mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, isMute)
        }
    }
}
