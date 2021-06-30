package com.example.covid19_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AbsListView
import androidx.work.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var stateAdapter: StateAdapter
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_header,list,false))
        fetchResults()
//        fetchvaccineresult()

        swipeToRefresh.setOnRefreshListener {
            fetchResults()
//            fetchvaccineresult()
        }
        initWorker()
        list.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
            override fun onScroll(
                view: AbsListView,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if (list.getChildAt(0) != null) {
                    swipeToRefresh.isEnabled = list.firstVisiblePosition === 0 && list.getChildAt(
                        0
                    ).getTop() === 0
                }
            }
        })
    }

//    private fun fetchvaccineresult() {
//        GlobalScope.launch {
//            val response1 = withContext(Dispatchers.Default) {
//                client2.api.clone().execute()
//            }
//            if(response1.isSuccessful){
//                val data1=Gson().fromJson(response1.body?.string(),Response::class.java)
//                launch(Dispatchers.Main) {
//                    data1.tested?.get(0)?.let { vaccinationdata(it) }
//                }
//            }
//        }
//    }

    private fun fetchResults() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) {
                Client.api.clone().execute()
            }
            if(response.isSuccessful){
                swipeToRefresh.isRefreshing = false
                val data=Gson().fromJson(response.body?.string(),Response2::class.java)
                launch(Dispatchers.Main) {
                    data.statewise?.get(0)?.let { bindCombinedData(it) }
                    bindstatewisedata(data.statewise?.let { data.statewise.subList(1, it.size) })

                }

            }
        }
    }

//    private fun vaccinationdata(tested: TestedItem) {
//        vaccinationtv.text=tested.totaldosesadministered
//
//    }

    private fun bindstatewisedata(let: List<StatewiseItem?>?) {
       stateAdapter = StateAdapter(let as List<StatewiseItem>)
        list.adapter=stateAdapter


    }

    private fun bindCombinedData(statewiseItem: StatewiseItem) {
        val lastUpdatedTime:String? =statewiseItem.lastupdatedtime
        val simpledateformat= SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        lastUpdatedTv.text="Last updated\n ${getTimeAgo(simpledateformat.parse(lastUpdatedTime))}"

        confirmedTv.text=statewiseItem.confirmed
        recoveredtv.text=statewiseItem.recovered
        activetv.text=statewiseItem.active
        deathtv.text=statewiseItem.deaths


    }


      @InternalCoroutinesApi
    private fun initWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val notificationWorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "JOB_TAG",
            ExistingPeriodicWorkPolicy.KEEP,
            notificationWorkRequest
        )
    }

}
fun getTimeAgo(past: Date): String {
    val now = Date()
    val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
    val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)

    return when {
        seconds < 60 -> {
            "Few seconds ago"
        }
        minutes < 60 -> {
            "$minutes minutes ago"
        }
        hours < 24 -> {
            "$hours hour ${minutes % 60} min ago"
        }
        else -> {
            SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
        }
    }
}

