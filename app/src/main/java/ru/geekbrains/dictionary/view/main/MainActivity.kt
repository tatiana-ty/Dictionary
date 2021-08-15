package ru.geekbrains.dictionary.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.dictionary.utils.convertMeaningsToString
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.core.BaseActivity
import ru.geekbrains.core.databinding.LoadingLayoutBinding
import ru.geekbrains.dictionary.R
import ru.geekbrains.dictionary.databinding.ActivityMainBinding
import ru.geekbrains.dictionary.view.DescriptionActivity
import ru.geekbrains.dictionary.view.main.adapter.MainAdapter
import ru.geekbrains.historyscreen.view.HistoryActivity
import ru.geekbrains.model.AppState
import ru.geekbrains.model.DataModel
import ru.geekbrains.utils.network.isOnline

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl
                    )
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingBinding = LoadingLayoutBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initViewModel()
        binding.searchLayout.setEndIconOnClickListener {
            println("here")
            model.getData(binding.tvSearch.text.toString(), isOnline(applicationContext))
        }
        recyclerView = binding.mainActivityRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.adapter = adapter
        model.getData("hi", true)
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
