package tweentyscoops.com.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.fragment_something.*
import tweentyscoops.com.myapplication.R

class SomethingFragment : Fragment() {

    private val userId by lazy {
        arguments?.getString("userId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_something, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("POND", "userId: $userId")
        btnActivity.setOnClickListener {
            findNavController(it).navigate(R.id.action_navigate_main)
        }
    }
}