package com.example.counterdatabase.ui.highlights

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.counterdatabase.databinding.ActivityFullscreenVideoBinding

class FullscreenVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenVideoBinding
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        val videoUrl = intent.getStringExtra("video_url")
        val playbackPosition = intent.getLongExtra("playback_position", 0)

        if (videoUrl != null) {
            initializePlayer(videoUrl, playbackPosition)
        }

        binding.exitFullscreenButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("playback_position", player?.currentPosition ?: 0)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun initializePlayer(videoUrl: String, playbackPosition: Long) {
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.setMediaItem(mediaItem)
        player?.seekTo(playbackPosition)
        player?.prepare()
        player?.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}