package syr.project.medical_assistant_implement.wiki

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_wiki.*
import syr.project.medical_assistant_implement.R

class WikiActivity : YouTubeBaseActivity() {
    companion object {
        val VIDEO_ID: String = "F-W6iHJHXr8";
        val YOUTUBE_API_KEY: String = "AIzaSyA9vlaTnnOYDgTcUvNBxo2cHDHZ47Y_c1E"
    }

    var seekTime = 0;
    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki)
        initUI()
    }

    private fun initUI() {
        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                youtubeplayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youtubeplayer?.loadVideo(VIDEO_ID)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Something went wrong !! ", Toast.LENGTH_SHORT).show()
            }
        }

        btnPlay.setOnClickListener(View.OnClickListener { v ->
            youtubePlayer.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
        })
    }
}