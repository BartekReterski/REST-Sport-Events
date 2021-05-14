package com.sportdata.dazn.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sportdata.dazn.R
import java.util.zip.Inflater

class EventFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.event_layout, container, false)

    companion object {
        fun newInstance(): EventFragment = EventFragment()
    }
}