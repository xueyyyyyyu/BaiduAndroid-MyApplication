package com.example.myapplication

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.general.general
import com.example.myapplication.general.generalAdapter
import com.example.myapplication.general.generalDao
import com.example.myapplication.general.generalDatabase
import com.example.myapplication.key.key
import com.example.myapplication.key.keyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private val keylist = ArrayList<key>()
    private val generallist = ArrayList<general>()
    private lateinit var logo: ImageView
    private lateinit var msetting: ImageView
    private lateinit var generalAdapter: generalAdapter
    private lateinit var generalDao: generalDao
    private val ADD_NEWS_REQUEST_CODE = 101

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val i_view = view.findViewById<ImageView>(R.id.weatherimage)
        i_view.alpha = 0f
        val fadeInAnimation = ObjectAnimator.ofFloat(i_view, "alpha", 0f, 1f)
        fadeInAnimation.duration = 5000
        fadeInAnimation.start()
        val loadingDuration: Long = 300
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loadingProgressBar)
        i_view.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            Handler().postDelayed({
                loadingProgressBar.visibility = View.GONE
                val intent = Intent(requireContext(), Weather::class.java)
                startActivity(intent)
                requireActivity().finish()
            }, loadingDuration)
        }

        // 初始化keylist
        initkey()
        val keylayoutManager = LinearLayoutManager(requireContext())
        val keyrecyclerView: RecyclerView = view.findViewById(R.id.hotspot)
        keyrecyclerView.layoutManager = keylayoutManager
        keyrecyclerView.adapter = keyAdapter(keylist)

        // 获取GeneralDatabase实例
        val generalDatabase = generalDatabase.getDatabase(requireContext())
        generalDao = generalDatabase.generalDao()

        // 初始化generallist并从数据库中加载数据
        initgeneral()
        generalAdapter = generalAdapter(generallist, generalDao)
        val generallayoutManager = LinearLayoutManager(requireContext())
        val generalrecyclerView: RecyclerView = view.findViewById(R.id.general)
        generalrecyclerView.layoutManager = generallayoutManager
        generalrecyclerView.adapter = generalAdapter

        logo = view.findViewById(R.id.logo)
        logo.setOnClickListener {
            onImageClick()
        }
        msetting = view.findViewById(R.id.msetting)
        msetting.setOnClickListener {
            showPopupMenu()
        }
        return view
    }

    private fun initkey() {
        repeat(1) {
            keylist.add(key("加强不同文明交流对话", "置顶", "XX新闻"))
            keylist.add(key("2000多名武警驰援北京房山", "置顶", "XX社"))
            keylist.add(key("卡努升级为超强台风", "置顶","XX时报" ))
            keylist.add(key("加强不同文明交流对话", "置顶", "XX新闻"))
            keylist.add(key("2000多名武警驰援北京房山", "置顶", "XX社"))
            keylist.add(key("卡努升级为超强台风", "置顶","XX时报" ))
            keylist.add(key("加强不同文明交流对话", "置顶", "XX新闻"))
            keylist.add(key("2000多名武警驰援北京房山", "置顶", "XX社"))
            keylist.add(key("卡努升级为超强台风", "置顶","XX时报" ))
            keylist.add(key("加强不同文明交流对话", "置顶", "XX新闻"))
            keylist.add(key("2000多名武警驰援北京房山", "置顶", "XX社"))
            keylist.add(key("卡努升级为超强台风", "置顶","XX时报" ))
        }
    }

    private fun initgeneral() {
        // 从数据库中加载数据到 generallist，仅加载一次数据
        if (generallist.isEmpty()) {
            // 使用 CoroutineScope 替代 GlobalScope，方便取消任务
            CoroutineScope(Dispatchers.IO).launch {
                val generalListFromDB = generalDao.getAllGenerals()
                withContext(Dispatchers.Main) {
                    generallist.addAll(generalListFromDB)
                    generalAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    // 启动AddNewsActivity
    private fun startAddNewsActivity() {
        val intent = Intent(requireContext(), AddNewsActivity::class.java)
        startActivityForResult(intent, ADD_NEWS_REQUEST_CODE)
    }

    // 处理从AddNewsActivity返回的数据
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NEWS_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            // 获取从 AddNewsActivity 传回来的数据
            val title = data.getStringExtra("title")
            val from = data.getStringExtra("from")
            val mantleImageUrl = data.getStringExtra("mantleImageUrl")
            val content = data.getStringExtra("content")

            // 创建新的 general 对象，并设置图片URL
            val newGeneral = general(
                imageResource = R.drawable.ic_launcher_background,
                title = title ?: "",
                content = content ?: "",
                iconResource = R.drawable.ic_fire,
                label = "热点",
                source = from ?: "",
                imageUrl = mantleImageUrl ?: ""
            )

            // 将新的 general 对象插入数据库，并更新 generallist
            GlobalScope.launch(Dispatchers.IO) {
                generalDao.insertGeneral(newGeneral)
                withContext(Dispatchers.Main) {
                    generalAdapter.insertGeneralAtTop(newGeneral)
                }
            }
        }
    }


    fun onImageClick() {
        val fadeInAnimation = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f)
        fadeInAnimation.duration = 1000
        fadeInAnimation.start()
    }

    fun showPopupMenu() {
        val popupMenu = PopupMenu(requireContext(), msetting, Gravity.END, 0, 0)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.login -> {
                    showToast("点击了登录")
                    true
                }
                R.id.sign -> {
                    showToast("点击了注册")
                    true
                }
                R.id.addnews -> {
                    startAddNewsActivity() // 启动AddNewsActivity
                    true
                }
                R.id.set -> {
                    showToast("点击了设置")
                    true
                }
                R.id.logout -> {
                    showToast("点击了退出")
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}