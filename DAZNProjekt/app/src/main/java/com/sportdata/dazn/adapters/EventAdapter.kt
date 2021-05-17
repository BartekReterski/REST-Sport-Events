package com.sportdata.dazn.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sportdata.dazn.R
import com.sportdata.dazn.models.SportEvents
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EventAdapter(val list: List<SportEvents>): RecyclerView.Adapter<SportEventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportEventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_layout_items, parent, false)
        return SportEventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SportEventViewHolder, position: Int) {

        return holder.bind(list[position])

    }
}

class SportEventViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    private val title: TextView = itemView.findViewById(R.id.textViewTitle)
    private val subtitle:TextView = itemView.findViewById(R.id.textViewSubtitle)
    private val eventData:TextView = itemView.findViewById(R.id.textViewEventDate)
    private val imageEvent:ImageView=itemView.findViewById(R.id.imageView)


    fun bind(data: SportEvents) {

        //przypisanie danych do struktury modelu

        title.text=data.title
        subtitle.text=data.subtitle

        //format daty oraz przypisanie skonwertowanego typu daty do elementu interfejsu

        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"    //wzorce wejściowe daty i wyjściowy taki jaki ma być na liście
        val outputPattern = "yyyy-MM-dd HH:mm"
        var inputDate: LocalDateTime? = null
        var outputDate: String? = null
        val inputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(inputPattern, Locale.ENGLISH)
        val outputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(outputPattern, Locale.ENGLISH)

        inputDate = LocalDateTime.parse(data.date, inputFormatter)
        outputDate = outputFormatter.format(inputDate)
        eventData.text=outputDate

        //wycięcie z formatowanej daty czasu
        val cutTime=outputDate.chunked(10)

        //data wczorajsza
        var yesterday: LocalDate?=null
        yesterday = LocalDate.now().minusDays(1L)

        //pobranie aktualnej daty
        val currentDateTime=LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)

        //przypisanie daty i jej formatowanie w przypadku daty dzisiejsze, wczorajszej
        if(outputDate.contains(currentDateTime)){

            eventData.text="Today "+cutTime[1]
        }
        if(outputDate.contains(yesterday.toString())){

            eventData.text="Yesterday "+cutTime[1]
        }

        //użycie biblioteki picasso do pobrania i wyświetlenia obrazka z URL oraz Intent do filmu z URL
        val imageURL=data.imageUrl
        val videoURL=data.videoUrl
        val picasso= Picasso.get().load(imageURL).into(imageEvent)
        if(imageURL.isNullOrEmpty()){

            val noimage=Picasso.get().load(R.drawable.ic_launcher_foreground).into(imageEvent)
        }

        itemView.setOnClickListener {

            val intent= Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(videoURL),"video/*")
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            itemView.context.startActivity(intent)
        }

    }

}