package ru.geekbrains.historyscreen.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.ru.history.R
import geekbrains.ru.history.databinding.ActivityHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.core.BaseActivity
import ru.geekbrains.core.databinding.LoadingLayoutBinding
import ru.geekbrains.model.AppState
import ru.geekbrains.model.DataModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        loadingBinding = LoadingLayoutBinding.inflate(layoutInflater)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.layoutManager = LinearLayoutManager(baseContext)
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
