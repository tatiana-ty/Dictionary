package ru.geekbrains.dictionary.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import geekbrains.ru.translator.utils.convertMeaningsToString
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.dictionary.R
import ru.geekbrains.dictionary.databinding.ActivityMainBinding
import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.view.DescriptionActivity
import ru.geekbrains.dictionary.view.base.BaseActivity
import ru.geekbrains.dictionary.view.main.adapter.MainAdapter

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener, emptyList()) }

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
                        data.meanings[0].imageUrl
                    )
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initViewModel()
        binding.search.setEndIconOnClickListener {
            println("here")
            model.getData(binding.tvSearch.text.toString(), true)
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

}
