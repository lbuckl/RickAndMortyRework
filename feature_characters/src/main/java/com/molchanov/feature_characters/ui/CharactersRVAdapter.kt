package com.molchanov.feature_characters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.feature_characters.databinding.FragmentCharactersRvItemBinding
import com.molchanov.domain.characters.*

/**
 * Адаптер для отображения и взаимодействия персонажей
 */
class CharactersRVAdapter(
    private val callback: OnListItemClickListener
) : RecyclerView.Adapter<CharactersBaseViewHolder>() {

    private var characterList: MutableList<Character> = mutableListOf()

    interface OnListItemClickListener {
        fun onItemClick(data: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersBaseViewHolder {
        val binding = FragmentCharactersRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return CharactersDefaultViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharactersBaseViewHolder, position: Int) {
        holder.bind(characterList[position])

        holder.itemView.setOnClickListener {
            callback.onItemClick(characterList[position])
        }
    }

    fun replaceData(characters: List<Character>) {
        characterList = characters.toMutableList()
        notifyDataSetChanged()
    }

    fun addData(character: Character){

        characterList.add(character)

        notifyItemInserted(characterList.size - 1)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}