package co.develhope.meteoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import co.develhope.meteoapp.databinding.ActivityMainBinding
import co.develhope.meteoapp.network.NetworkObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeScreen())
                R.id.today -> replaceFragment(TodayScreen())
                R.id.tomorrow -> replaceFragment(TodayScreen())
            }
            true
        }
        replaceFragment(HomeScreen())

        lifecycleScope.launch{
            try {
                Log.d("ForecastLog", "weekly: ${NetworkObject.getWeeklySummary()}")
                Log.d("ForecastLog", "hourly: ${NetworkObject.getHourlyForecastForASpecificDay()}")
           } catch (e: Exception){
                e.printStackTrace()
                Log.d("ForecastLog", e.toString())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}