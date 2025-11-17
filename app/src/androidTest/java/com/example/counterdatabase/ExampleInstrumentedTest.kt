package com.example.counterdatabase

import android.content.Intent
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.counterdatabase.data.Highlight
import com.example.counterdatabase.databinding.ActivityHighlightDetailsBinding
import com.example.counterdatabase.ui.highlights.HighlightDetailsActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.counterdatabase", appContext.packageName)
    }

    // ========== HighlightDetailsActivity Tests ==========

    private val fullHighlight = Highlight(
        id = "aus2025_chopper2kvsmouzonmirage1",
        name = "Souvenir Charm | Austin 2025 Highlight | chopper Double Kill",
        description = "chopper gets a double kill on the A-site defense from Balcony on Mirage.",
        tournament_event = "Austin 2025",
        team0 = "Team Spirit",
        team1 = "MOUZ",
        stage = "Quarterfinal",
        map = "de_mirage",
        market_hash_name = "Souvenir Charm | Austin 2025 Highlight | chopper Double Kill",
        image = "image_url",
        video = "video_url"
    )

    private val missingDataHighlight = Highlight(
        id = "some_id", name = "Quick Scope", description = null, tournament_event = "",
        team0 = null, team1 = "Team Bravo", stage = null, map = "", market_hash_name = null,
        image = "image_url", video = null
    )

    @Test
    fun displaysAllDetails_whenHighlightIsComplete() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), HighlightDetailsActivity::class.java)
            .putExtra("highlight", fullHighlight)

        ActivityScenario.launch<HighlightDetailsActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                val binding = ActivityHighlightDetailsBinding.bind(activity.findViewById(android.R.id.content))
                assertEquals("Souvenir Charm | Austin 2025 Highlight | chopper Double Kill", binding.highlightName.text)
                assertEquals("chopper gets a double kill on the A-site defense from Balcony on Mirage.", binding.highlightDescription.text)
                assertEquals("Tournament: Austin 2025", binding.highlightTournament.text)
                assertEquals("Teams: Team Spirit vs MOUZ", binding.highlightTeams.text)
                assertEquals("Stage: Quarterfinal", binding.highlightStage.text)
                assertEquals("Map: de_mirage", binding.highlightMap.text)
                assertEquals(View.VISIBLE, binding.playerView.visibility)
            }
        }
    }

    @Test
    fun hidesEmptyViews_whenDataIsMissing() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), HighlightDetailsActivity::class.java)
            .putExtra("highlight", missingDataHighlight)

        ActivityScenario.launch<HighlightDetailsActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                val binding = ActivityHighlightDetailsBinding.bind(activity.findViewById(android.R.id.content))
                assertEquals("Quick Scope", binding.highlightName.text)
                assertEquals(View.GONE, binding.highlightDescription.visibility)
                assertEquals(View.GONE, binding.highlightTournament.visibility)
                assertEquals(View.GONE, binding.highlightTeams.visibility)
                assertEquals(View.GONE, binding.highlightStage.visibility)
                assertEquals(View.GONE, binding.highlightMap.visibility)
                assertEquals(View.GONE, binding.playerView.visibility)
                assertEquals(View.GONE, binding.fullscreenButton.visibility)
            }
        }
    }

    @Test
    fun playerIsReleased_onStop() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), HighlightDetailsActivity::class.java)
            .putExtra("highlight", fullHighlight) // Using fullHighlight as it has a video

        ActivityScenario.launch<HighlightDetailsActivity>(intent).use { scenario ->
            // Move to a state where player is guaranteed to be created
            scenario.moveToState(Lifecycle.State.STARTED)
            scenario.onActivity { activity ->
                assertNotNull("Player should be initialized by onStart", activity.player)
            }

            // Move to a state that triggers onStop()
            scenario.moveToState(Lifecycle.State.CREATED)
            scenario.onActivity { activity ->
                assertNull("Player should be released by onStop", activity.player)
            }
        }
    }
}