package com.baidu.androidlearn.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.baidu.androidlearn.home.components.news.NewsComponent
import com.baidu.androidlearn.home.components.news.NewsViewModel
import com.baidu.androidlearn.home.components.weather.WeatherComponent
import com.baidu.androidlearn.home.components.weather.WeatherViewModel
import com.baidu.androidlearn.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var weatherComponent: WeatherComponent? = null
    private var newsComponent: NewsComponent? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            _binding = this
        }

        // 独立的天气组件
        val weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        weatherComponent = WeatherComponent(viewLifecycleOwner, weatherViewModel, binding.componentWeather)


        // 独立的新闻组件 // todo check
        val newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsComponent = NewsComponent(viewLifecycleOwner, newsViewModel, binding.componentNews)

        /*val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = binding.textHome
        textView.setOnClickListener {
            homeViewModel.increment()
        }
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}