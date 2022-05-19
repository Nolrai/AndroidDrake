package com.garthskidstuff.drake.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.garthskidstuff.drake.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


// This gets new states from the

class MainFragment : Fragment(), View.OnClickListener, SurfaceHolder.Callback {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private lateinit var surfaceView: SurfaceView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        surfaceView = view.findViewById<SurfaceView>(R.id.surfaceView)
        val holder = surfaceView.holder
        holder.setFixedSize(20000, 20000)
//        surfaceView.setOnClickListener(this)
//        surfaceView.holder.addCallback(this)
        MainScope().launch {
            val state = viewModel.uiState
            isSurfaceDestroyed = false

            state.collect {
                val canvas = holder.lockCanvas(null)
                it.draw(canvas, requireContext())
                if (!isSurfaceDestroyed && !holder.isCreating) {
                    holder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onClick(view: View?) {
        viewModel.pushAction(UIAction.Click)
    }

    private var isSurfaceDestroyed: Boolean = false

    override fun surfaceCreated(holder: SurfaceHolder) {

    }

    override fun surfaceChanged(holder: SurfaceHolder, pixelFormat: Int, p2: Int, p3: Int) {
//        holder.setFixedSize(p2,p3)
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        isSurfaceDestroyed = true
    }


}
