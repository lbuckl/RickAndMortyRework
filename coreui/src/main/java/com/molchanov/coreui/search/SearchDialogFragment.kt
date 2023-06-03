package com.molchanov.coreui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molchanov.coreui.databinding.FragmentDialogSearchBinding

/**
 * Диологовое окно для формирования данных для писка
 */
class SearchDialogFragment : BottomSheetDialogFragment() {

    companion object {
        val instance = SearchDialogFragment()
        const val FRAGMENT_TAG = "SearchDialogFragment_IdentificationTag"
    }

    private var _binding: FragmentDialogSearchBinding? = null

    private val binding get() = _binding!!

    private var onSearchClickListener: OnSearchClickListener? = null

    private val textWatcher = object : TextWatcher {

        /**
         * Действие при изменении текста
         */
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            setElementVision()
        }

        /**
         * Действие перед изменением текста
         */
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //TODO Nothing
        }

        /**
         * Действие ппосле изменением текста
         */
        override fun afterTextChanged(s: Editable) {
            //TODO Nothing
        }
    }

    /**
     * Функция устанавливает видимость элементов при вводе/удалении текста
     */
    private fun setElementVision() {
        if (binding.searchEditText.text != null &&
            binding.searchEditText.text.toString().isNotEmpty()
        ) {
            binding.btnSearchDialog.visibility = View.VISIBLE
        } else {
            binding.btnSearchDialog.visibility = View.GONE
        }
    }

    /**
     * Функция кликкер для начала переода слова
     */
    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }

    fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearchDialog.setOnClickListener(onSearchButtonClickListener)
        binding.searchEditText.addTextChangedListener(textWatcher)

        setElementVision()
    }

    override fun onDestroyView() {
        _binding = null
        onSearchClickListener = null
        super.onDestroyView()
    }

    /**
     * Интервейс кликкера для начала перевода слова
     */
    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }
}
