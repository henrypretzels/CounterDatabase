package com.example.counterdatabase.ui.highlights

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.counterdatabase.data.Highlight
import com.example.counterdatabase.databinding.ActivityHighlightDetailsBinding

class HighlightDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHighlightDetailsBinding
    private var player: ExoPlayer? = null
    private var videoUrl: String? = null
    private var currentPlaybackPosition: Long = 0

    private val fullscreenResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            currentPlaybackPosition = result.data?.getLongExtra("playback_position", 0) ?: 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighlightDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val highlight = intent.getParcelableExtra<Highlight>("highlight")

        highlight?.let {
            binding.highlightName.text = it.name

            if (it.description.isNullOrEmpty()) {
                binding.highlightDescription.visibility = View.GONE
            } else {
                binding.highlightDescription.text = it.description
            }

            if (it.tournament_event.isNullOrEmpty()) {
                binding.highlightTournament.visibility = View.GONE
            } else {
                binding.highlightTournament.text = "Tournament: ${it.tournament_event}"
            }

            if (it.team0.isNullOrEmpty() || it.team1.isNullOrEmpty()) {
                binding.highlightTeams.visibility = View.GONE
            } else {
                binding.highlightTeams.text = "Teams: ${it.team0} vs ${it.team1}"
            }

            if (it.stage.isNullOrEmpty()) {
                binding.highlightStage.visibility = View.GONE
            } else {
                binding.highlightStage.text = "Stage: ${it.stage}"
            }

            if (it.map.isNullOrEmpty()) {
                binding.highlightMap.visibility = View.GONE
            } else {
                binding.highlightMap.text = "Map: ${it.map}"
            }

            if (it.video.isNullOrEmpty()) {
                binding.playerView.visibility = View.GONE
                binding.fullscreenButton.visibility = View.GONE
            } else {
                videoUrl = it.video
            }
        }

        binding.fullscreenButton.setOnClickListener {
            val intent = Intent(this, FullscreenVideoActivity::class.java)
            intent.putExtra("video_url", videoUrl)
            intent.putExtra("playback_position", player?.currentPosition ?: 0)
            fullscreenResultLauncher.launch(intent)
        }
    }

    private fun initializePlayer(videoUrl: String) {
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.setMediaItem(mediaItem)
        player?.seekTo(currentPlaybackPosition)
        player?.prepare()
        player?.playWhenReady = true
    }

    private fun releasePlayer() {
        player?.let {
            currentPlaybackPosition = it.currentPosition
            it.release()
            player = null
        }
    }

    override fun onStart() {
        super.onStart()
        videoUrl?.let { initializePlayer(it) }
    }

    override fun onResume() {
        super.onResume()
        if (player == null) {
            videoUrl?.let { initializePlayer(it) }
        }
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
}