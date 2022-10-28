package co.develhope.meteoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.develhope.meteoapp.databinding.ActivityMainBinding

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
                //Attenzione, per usare il terzo fragment rimpiazzare quest'ultima stringa con:
                //R.id.tomorrow -> replaceFragment(TomorrowScreen())

                else -> {
                    //TODO is needed?
                }

            }
            true
        }
        replaceFragment(HomeScreen())
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }
}