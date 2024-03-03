package com.codewithshadow.filmrave.presentation.intro

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codewithshadow.filmrave.R
import com.codewithshadow.filmrave.databinding.FragmentIntroBinding
import com.codewithshadow.filmrave.utils.DatastorePreferences
import com.codewithshadow.filmrave.utils.setSpannableText
import kotlinx.coroutines.launch

class IntroFragment : Fragment() {

    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleClickListeners()
    }

    private fun initView() {
        binding.apply {
            val privacyPolicyText = getString(R.string.privacy_policy)
            val termsOfUseText = getString(R.string.terms_of_use)

            val fullText = getString(R.string.app_terms)

            val spannable = SpannableStringBuilder(fullText)

            val privacyPolicyStart = fullText.indexOf(privacyPolicyText)
            val termsOfUseStart = fullText.indexOf(termsOfUseText)

            // Applying bold style to Privacy Policy and Terms of Use
            setSpannableText(requireActivity(), spannable, termsOfUseStart, termsOfUseText.length)
            setSpannableText(
                requireActivity(),
                spannable,
                privacyPolicyStart,
                privacyPolicyText.length
            )

            pptText.text = spannable
        }
    }

    private fun handleClickListeners() {
        binding.continueBtn.setOnClickListener {
            // Use lifecycleScope to update the value of intro_completed in DataStore asynchronously
            lifecycleScope.launch {
                DatastorePreferences.updateIntroCompleted(
                    requireContext(),
                    true
                ) // intro completed and updating value in datastore
            }
            // Navigate to the HomeFragment
            findNavController().navigate(
                R.id.action_introFragment_to_homeFragment
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}