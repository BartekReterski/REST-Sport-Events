package  com.sportdata.dazn.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sportdata.dazn.R
import com.sportdata.dazn.fragments.EventFragment
import com.sportdata.dazn.fragments.ScheduleFragment

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar = supportActionBar!!

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.navigationView)

        //wywołanie metody z eventami do poszczególnych fragmentów
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //wymuszenie wyboru pierwszej zakładki
        bottomNavigation.selectedItemId=R.id.navigation_sport_events

    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_sport_events-> {
                    toolbar.title = "Events"
                    val eventFragment = EventFragment.newInstance()
                    openFragment(eventFragment)
                    return@OnNavigationItemSelectedListener true

                }
                R.id.navigation_schedule -> {
                    toolbar.title = "Schedule"
                    val scheduleFragment = ScheduleFragment.newInstance()
                    openFragment(scheduleFragment)
                    return@OnNavigationItemSelectedListener true

                }

            }
            false
        }
    //zmiana fragmentów i operowanie między nimi w runtime
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}