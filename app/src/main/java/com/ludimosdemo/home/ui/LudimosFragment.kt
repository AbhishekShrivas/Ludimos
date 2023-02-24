package com.ludimosdemo.home.ui

import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.ludimosdemo.LudimosApplication
import com.ludimosdemo.R
import com.ludimosdemo.databinding.FragmentLudimosBinding
import com.ludimosdemo.home.viewmodels.LudimosViewModel
import com.ludimosdemo.models.BallTrackResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LudimosFragment : Fragment() {
    private var _binding: FragmentLudimosBinding? = null
    private val binding get() = _binding!!
    lateinit var ludimosViewModel: LudimosViewModel
    private lateinit var httpDataSourceFactory: HttpDataSource.Factory
    private lateinit var defaultDataSourceFactory: DefaultDataSourceFactory
    private lateinit var cacheDataSourceFactory: DataSource.Factory
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private val simpleCache: SimpleCache = LudimosApplication.simpleCache

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ludimosViewModel = ViewModelProvider(this)[LudimosViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLudimosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVideoCache()
        ludimosViewModel.getBallTrackResponse()

        ludimosViewModel.response.observe(viewLifecycleOwner, Observer {
//            it?.original_url?.let {
//                //video view implementation will go here for null safety
//            }
            val videoUri = Uri.parse(it.original_url)
            val mediaItem = MediaItem.fromUri(videoUri)
            val mediaSource = ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(mediaItem)

            binding.idExoPlayerVIew.player = simpleExoPlayer
            simpleExoPlayer.playWhenReady = true
            simpleExoPlayer.seekTo(0, 0)
            simpleExoPlayer.repeatMode = Player.REPEAT_MODE_OFF
            simpleExoPlayer.setMediaSource(mediaSource, true)
            simpleExoPlayer.prepare()

            simpleExoPlayer.addListener( object : Player.Listener{
                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        ExoPlayer.STATE_READY ->{

                        }
                        ExoPlayer.STATE_ENDED -> {

                        }
                    }
                }
            }
            )

            binding.btn.setOnClickListener {view->
//                drawLine(it.stump_line)
                if(binding.stumpline.visibility == View.INVISIBLE){
                    binding.stumpline.visibility = View.VISIBLE
                }else{
                    binding.stumpline.visibility = View.INVISIBLE
                }
            }

        })

    }

    private fun drawLine(stumps: List<Double>) {
        var rect = Rect()

    }

    private fun initVideoCache(){

        httpDataSourceFactory = DefaultHttpDataSource.Factory()
            .setAllowCrossProtocolRedirects(true)

        defaultDataSourceFactory = DefaultDataSourceFactory(
            requireContext(), httpDataSourceFactory
        )

        cacheDataSourceFactory = CacheDataSource.Factory()
            .setCache(simpleCache)
            .setUpstreamDataSourceFactory(httpDataSourceFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)

        simpleExoPlayer = SimpleExoPlayer.Builder(requireContext())
            .setMediaSourceFactory(DefaultMediaSourceFactory(cacheDataSourceFactory)).build()

    }

    companion object {

    }
}