package com.molchanov.feature_characters.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.molchanov.feature_characters.data.CharacterFilterData
import com.molchanov.feature_characters.databinding.FragmentDialogFilterCharactersBinding
import com.molchanov.coreui.R as coreUiR

/**
 * Диологовое окно для формирования данных для фильтрации песонажей
 */
class CharactersFilterDialogFragment : BottomSheetDialogFragment() {

    companion object {
        val instance = CharactersFilterDialogFragment()
        const val FRAGMENT_TAG = "FilterDialogFragment_IdentificationTag"

        private const val STATUS_ALIVE = "Alive"
        private const val STATUS_DEAD = "Dead "
        private const val STATUS_UNKNOWN = "Unknown"

        private const val GENDER_MALE = "Male"
        private const val GENDER_FEMALE = "Female"
        private const val GENDER_GENDERLESS = "Genderless "
        private const val GENDER_UNKNOWN = "Unknown"
    }

    private var _binding: FragmentDialogFilterCharactersBinding? = null

    private val binding get() = _binding!!

    private var onFilterClickListener: OnFilterClickListener? = null

    private var charStatus: String? = null

    private var charGender: String? = null

    private lateinit var statusChipList: List<Chip>

    private lateinit var genderChipList: List<Chip>

    /**
     * Функция кликкер для отправки фильтра
     */
    private val onFilterButtonClickListener =
        View.OnClickListener {
            onFilterClickListener?.onClick(
                CharacterFilterData(
                    binding.nameEditText.text.toString(),
                    charStatus,
                    binding.speciesEditText.text.toString(),
                    charGender
                )
            )
            dismiss()
        }

    internal fun setOnFilterClickListener(listener: OnFilterClickListener) {
        onFilterClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDialogFilterCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFilterDialog.setOnClickListener(onFilterButtonClickListener)

        initChips()
    }

    private fun initChips() {
        with(binding) {

            statusChipList = listOf(
                statusNo, statusAlive, statusDead, statusUnknown
            )

            genderChipList = listOf(
                genderNo, genderMale, genderFemale, genderGenderless, genderUnknown
            )

            setActiveChipColor(statusChipList, statusNo)

            setActiveChipColor(genderChipList, genderNo)

            try {
                cgStatus.setOnCheckedStateChangeListener { group, checkedIds ->

                    if (checkedIds.size > 0) {
                        when (group.checkedChipId) {
                            statusNo.id -> {
                                charStatus = null

                                setActiveChipColor(statusChipList, statusNo)
                            }

                            statusAlive.id -> {
                                charStatus = STATUS_ALIVE

                                setActiveChipColor(statusChipList, statusAlive)
                            }

                            statusDead.id -> {
                                charStatus = STATUS_DEAD

                                setActiveChipColor(statusChipList, statusDead)
                            }

                            statusUnknown.id -> {
                                charStatus = STATUS_UNKNOWN

                                setActiveChipColor(statusChipList, statusUnknown)
                            }
                        }
                    }
                }

                cgGender.setOnCheckedStateChangeListener { group, checkedIds ->

                    if (checkedIds.size > 0) {
                        when (group.checkedChipId) {

                            genderNo.id -> {
                                charGender = null

                                setActiveChipColor(genderChipList, genderNo)
                            }

                            genderMale.id -> {
                                charGender = GENDER_MALE

                                setActiveChipColor(genderChipList, genderMale)
                            }

                            genderFemale.id -> {
                                charGender = GENDER_FEMALE

                                setActiveChipColor(genderChipList, genderFemale)
                            }

                            genderGenderless.id -> {
                                charGender = GENDER_GENDERLESS

                                setActiveChipColor(genderChipList, genderGenderless)
                            }

                            genderUnknown.id -> {
                                charGender = GENDER_UNKNOWN

                                setActiveChipColor(genderChipList, genderUnknown)
                            }
                        }
                    }
                }
            } catch (e: java.lang.IndexOutOfBoundsException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Функция закращивает в светлый цвет нажатый чип
     * и в тёмный все остальные
     */
    private fun setActiveChipColor(chipList: List<Chip>, active: Chip) {

        chipList.forEach {
            it.setChipBackgroundColorResource(coreUiR.color.yellow_dark)
        }

        active.setChipBackgroundColorResource(coreUiR.color.yellow_bright)

    }

    override fun onDestroyView() {
        _binding = null
        onFilterClickListener = null
        super.onDestroyView()
    }

    /**
     * Интервейс кликкера для начала перевода слова
     */
    interface OnFilterClickListener {

        fun onClick(filterWords: CharacterFilterData)
    }
}
