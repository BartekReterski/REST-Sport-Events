package com.sportdata.dazn.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sportdata.dazn.R
import com.sportdata.dazn.adapters.EventAdapter
import com.sportdata.dazn.api.ApiService
import com.sportdata.dazn.interfaces.ApiInterface
import com.sportdata.dazn.models.SportEvents
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class EventFragment: Fragment() {

    companion object {

        fun newInstance(): EventFragment {
            return EventFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(
            R.layout.event_layout, container,
            false
        )

        try {
            val activity = activity as Context
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

            val request = ApiService.buildApiService(ApiInterface::class.java)
            val call = request.getSportEvents()


            call.enqueue(object : Callback<List<SportEvents>> {
                override fun onFailure(call: Call<List<SportEvents>>, t: Throwable) {
                    Toast.makeText(activity,"Something was wrong"+t.localizedMessage,Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<SportEvents>>, response: Response<List<SportEvents>>) {

                    progressBar.visibility = (View.GONE)
                    val recyclerView = view.findViewById<RecyclerView>(R.id.event_recyclerView)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)

                        //lista eventow
                       // val eventList: List<SportEvents> = response.body()!!
                        val listMutable= response.body()!!.toMutableList()

                        //sortowanie listy po dacie rosnąco
                        listMutable.sortBy { it.date }
                        adapter = EventAdapter(listMutable)

                    }

                }
            })
        }catch (e:Exception){
            Toast.makeText(activity,"Something was wrong"+e.localizedMessage,Toast.LENGTH_LONG).show()

        }
        return view
    }

}






