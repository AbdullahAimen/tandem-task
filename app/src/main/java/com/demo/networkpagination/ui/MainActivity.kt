package com.demo.networkpagination.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.demo.networkpagination.R
import com.demo.networkpagination.repos.communityRepo.CommunityRepo
import com.demo.networkpagination.ui.adapter.CardsAdapter
import com.demo.networkpagination.ui.adapter.LoadStateAdapter
import com.demo.networkpagination.viewModel.CommunityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: CommunityViewModel
    private val mCardsAdapter by inject<CardsAdapter>()
    private val mCommunityRepo by inject<CommunityRepo>()
    private val mLoadStateAdapter by inject<LoadStateAdapter>()
//    private lateinit var mLoadStateAdapter: LoadStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, MyViewModelFactory(mCommunityRepo))
            .get(CommunityViewModel::class.java)
        setContentView(R.layout.activity_main)
        setupRecycler()
    }

    private fun setupRecycler() {
        recycler.adapter = mCardsAdapter.withLoadStateFooter(
            footer = mLoadStateAdapter,
        )

        lifecycleScope.launch {
            mCardsAdapter.loadStateFlow.collectLatest { loadStates ->
                swipe_refresh.isRefreshing = loadStates.refresh is LoadState.Loading
                mLoadStateAdapter.loadState = when {
                    loadStates.refresh is LoadState.NotLoading -> loadStates.append
                    else -> loadStates.refresh
                }
            }
        }

        lifecycleScope.launch {
            mViewModel.communityFlow.collectLatest { pagingData ->
                mCardsAdapter.submitData(pagingData)
            }
        }

        swipe_refresh.setOnRefreshListener {
            mCardsAdapter.refresh()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class MyViewModelFactory(vararg params: Any) :
        NewInstanceFactory() {
        private val mParams: Array<Any> = params as Array<Any>
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass == CommunityViewModel::class.java) {
                CommunityViewModel(mParams[0] as CommunityRepo) as T
            } else {
                super.create(modelClass)
            }
        }

    }
}
