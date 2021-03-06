package com.sportdata.dazn.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sportdata.dazn.R
import com.sportdata.dazn.adapters.ScheduleAdapter
import com.sportdata.dazn.api.ApiService
import com.sportdata.dazn.interfaces.ApiInterface
import com.sportdata.dazn.models.ScheduleEvents
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ScheduleFragment: Fragment() {

    companion object {

        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(
                R.layout.schedule_layout, container,
                false
        )

        //uruchomienie aktualizacji harmonogramu co 30 sekund
        val handler=Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {

        try {
            val textEmptyList= view.findViewById<TextView>(R.id.schedule_empty)
            val activity = activity as Context
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

            val request = ApiService.buildApiService(ApiInterface::class.java)
            val call = request.getSportSchedule()


            call.enqueue(object : Callback<List<ScheduleEvents>> {
                override fun onFailure(call: Call<List<ScheduleEvents>>, t: Throwable) {
                    Toast.makeText(activity,"Something was wrong"+t.localizedMessage,Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<List<ScheduleEvents>>, response: Response<List<ScheduleEvents>>) {

                    progressBar.visibility = (View.GONE)
                    val recyclerView = view.findViewById<RecyclerView>(R.id.schedule_recyclerView)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)

                        //lista harmonogramu
                          val mutableList=response.body()!!.toMutableList()
                          val currentDateTime= LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)

                        //sprawdzanie i dodawanie jedynie zdarze?? z  daty dzisiejszej
                          val scheduleListToday= mutableListOf<ScheduleEvents>()
                        for (scheduleEvents in mutableList) {
                            if(scheduleEvents.date.contains(currentDateTime)){
                               scheduleListToday.add(scheduleEvents)
                            }
                        }

                        //sortowanie listy po dacie rosn??co
                          scheduleListToday.sortBy { it.date }

                        //zapami??tanie pozycji recyclerview
                        adapter?.stateRestorationPolicy=RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                        adapter = ScheduleAdapter(scheduleListToday)

                        if(scheduleListToday.isEmpty()){

                            textEmptyList.visibility=View.VISIBLE
                        }
                        if(scheduleListToday.isNotEmpty()){

                            textEmptyList.visibility=View.GONE
                        }


                    }

                }
            })
        }catch (e:Exception){
            //Toast.makeText(activity,"Something was wrong"+e.localizedMessage,Toast.LENGTH_LONG).show()

        }
                handler.postDelayed(this, 30000)
              //  Toast.makeText(activity,"Schedule updated",Toast.LENGTH_LONG).show()
            }
        })

        return view
    }

}






