package com.molchanov.feature_characters.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.coreui.utils.loadImageFromUrl
import com.molchanov.feature_characters.databinding.FragmentCharactersRvItemBinding
import com.molchanov.domain.characters.*


abstract class CharactersBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: Character)
}

class CharactersDefaultViewHolder(view: View) : CharactersBaseViewHolder(view) {

    override fun bind(data: Character) {
        FragmentCharactersRvItemBinding.bind(itemView).also {
            it.ivCharacterIcon.loadImageFromUrl(data.imgUrl)
            it.tvCharacterName.text = "Name: ${data.name}"
            it.tvCharacterSpec.text = "Spec: ${data.spec}"
            it.tvCharacterGender.text = "Gender: ${data.gender}"
            it.tvCharacterStatus.text = "Status: ${data.status}"
        }
    }
}