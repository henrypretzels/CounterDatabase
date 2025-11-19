package com.example.counterdatabase.ui.highlights

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Highlight
import com.example.counterdatabase.databinding.ActivityHighlightDetailsBinding

class HighlightDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHighlightDetailsBinding
    internal var player: ExoPlayer? = null
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val highlight = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("highlight", Highlight::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Highlight>("highlight")
        }

        highlight?.let {
            binding.highlightName.text = it.name

            if (it.description.isNullOrEmpty()) {
                binding.descriptionCard.visibility = View.GONE
            } else {
                binding.highlightDescription.text = it.description
            }

            if (!it.tournament_event.isNullOrEmpty()) {
                binding.tournamentContainer.visibility = View.VISIBLE
                binding.highlightTournament.text = it.tournament_event
            } else {
                binding.tournamentContainer.visibility = View.GONE
            }

            if (!it.team0.isNullOrEmpty() && !it.team1.isNullOrEmpty()) {
                binding.teamsContainer.visibility = View.VISIBLE
                binding.highlightTeams.text = "${it.team0} vs ${it.team1}"
            } else {
                binding.teamsContainer.visibility = View.GONE
            }

            if (!it.stage.isNullOrEmpty()) {
                binding.stageContainer.visibility = View.VISIBLE
                binding.highlightStage.text = it.stage
            } else {
                binding.stageContainer.visibility = View.GONE
            }

            if (!it.map.isNullOrEmpty()) {
                binding.mapContainer.visibility = View.VISIBLE
                binding.highlightMap.text = it.map
            } else {
                binding.mapContainer.visibility = View.GONE
            }

            if (it.video.isNullOrEmpty()) {
                binding.videoContainer.visibility = View.GONE
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
