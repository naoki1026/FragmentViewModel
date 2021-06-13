package com.example.fragmentviewmodelupdate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentviewmodel.databinding.FragmentSubBinding

class SubFragment : Fragment() {

    private var _binding : FragmentSubBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel : MainViewModel

    companion object {
        fun newInstance() = SubFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubBinding.inflate(layoutInflater, container, false)

        val msgObserver = androidx.lifecycle.Observer<String> { newMessage ->
            binding.message2.setText(newMessage, TextView.BufferType.NORMAL)
        }
        mainViewModel.message2.observe(viewLifecycleOwner, msgObserver)

        val sdMsgObserver = androidx.lifecycle.Observer<String> { newMessage ->
            binding.sdMessage2.setText(newMessage, TextView.BufferType.NORMAL)
        }
        mainViewModel.share_data.value!!.share_message
            .observe(viewLifecycleOwner, sdMsgObserver)

        binding.button2.setOnClickListener {
            mainViewModel.count()
            mainViewModel.message2.value = "<< CHANGE by 2st [${mainViewModel.count.value}] >>"
            mainViewModel.share_data.value!!.count()
            mainViewModel.share_data.value!!.share_message.value =
                "<< CHANGE by 2st [${mainViewModel.share_data.value!!.share_count.value}] >>"
        }
        return binding.root
    }
}