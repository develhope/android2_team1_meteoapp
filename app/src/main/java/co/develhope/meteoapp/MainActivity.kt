package co.develhope.meteoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import co.develhope.meteoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            setBottomBarClick(it)
        }
        replaceFragment(HomeScreenFragment())

    }

    private fun setBottomBarClick(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.home -> replaceFragment(HomeScreenFragment())
            R.id.search -> replaceFragment(SearchPageFragment())
            R.id.today -> {
                ForecastInfoObject.getSelectedTodayInfo(0)
                    ?.let { it -> ForecastInfoObject.saveSelectedCardInfo(it) }
                replaceFragment(TodayScreenFragment())
            }
            R.id.tomorrow -> {
                ForecastInfoObject.getSelectedTodayInfo(1)
                    ?.let { it -> ForecastInfoObject.saveSelectedCardInfo(it) }
                replaceFragment(TodayScreenFragment())
            }
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    /*
    override fun onBackPressed() {
        val exit = QuitAppFragmentDialog()
        exit.show(supportFragmentManager, null)
    }

     */
}