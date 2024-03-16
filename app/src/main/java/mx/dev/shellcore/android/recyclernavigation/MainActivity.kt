package mx.dev.shellcore.android.recyclernavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.dev.shellcore.android.recyclernavigation.databinding.ActivityMainBinding
import mx.dev.shellcore.android.recyclernavigation.detail.fragment.DetailFragment
import mx.dev.shellcore.android.recyclernavigation.list.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listFragment = ListFragment.getInstance { selectedInfo ->
            supportFragmentManager.beginTransaction()
                .replace(binding.mainFragmentContainer.id, DetailFragment.getInstance().apply {
                    this.info = selectedInfo
                })
                .addToBackStack(null)
                .commit()
        }


        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragmentContainer.id, listFragment)
            .addToBackStack(null)
            .commit()
    }
}