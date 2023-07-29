package com.baidu.androidlearn.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baidu.androidlearn.mine.databinding.FragmentMineBinding

class MineFragment : Fragment() {

    private var controller: MineController? = null
    private var view: MineView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMineBinding.inflate(inflater, container, false)
        val model = MineModel()
        view = MineView(binding, model).apply {
            // 早期的一些项目中可能没有拆分独立的View或Controller，而是直接让Activity或者Fragment充当了两个角色
            // 这样会更容易导致庞大的Activity/Fragment
            controller = MineController(this, model)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view = null
        controller = null
    }
}