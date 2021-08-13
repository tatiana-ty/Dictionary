package ru.geekbrains.dictionary.view.translation

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.dictionary.R
import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.view.base.BaseActivity

class TranslationActivity : BaseActivity<AppState, TranslationInteractor>() {

    override lateinit var model: TranslationViewModel
    private val adapter: TranslationAdapter by lazy { TranslationAdapter() }
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        recyclerView = findViewById(R.id.history_activity_recyclerview)
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
        if (recyclerView.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: TranslationViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@TranslationActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        recyclerView.adapter = adapter
    }
}
