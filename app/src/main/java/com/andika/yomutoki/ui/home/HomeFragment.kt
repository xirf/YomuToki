package com.andika.yomutoki.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andika.yomutoki.Anime
import com.andika.yomutoki.ListAnimeAdapter
import com.andika.yomutoki.R
import com.andika.yomutoki.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var rvAnime: RecyclerView
    private var list: ArrayList<Anime> = ArrayList()

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rvAnime = root.findViewById(R.id.rv_home)

        list.addAll(getListAnime())

        rvAnime.layoutManager = LinearLayoutManager(rvAnime.context)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnime.adapter = listAnimeAdapter



        return root
    }

    private fun getListAnime(): Collection<Anime> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataEnglishTitle = resources.getStringArray(R.array.data_englishTitle)
        val dataJapaneseTitle = resources.getStringArray(R.array.data_japaneseTitle)
        val dataSynopsis = resources.getStringArray(R.array.data_synopsis)
        val dataEpisodes = resources.getStringArray(R.array.data_episodes)
        val dataType = resources.getStringArray(R.array.data_type)
        val dataRated = resources.getStringArray(R.array.data_rated)
        val dataGenres = resources.getStringArray(R.array.data_genres)
        val dataStudios = resources.getStringArray(R.array.data_studios)
        val dataStatus = resources.getStringArray(R.array.data_status)
        val dataPicture = resources.getStringArray(R.array.data_picture)
        val dataAnimeCover = resources.getStringArray(R.array.data_cover)
        val dataCharacters = resources.getStringArray(R.array.data_characters)
        val listAnime = ArrayList<Anime>()
        for (i in dataTitle.indices) {
            val anime = Anime(
                title = dataTitle[i],
                englishTitle = dataEnglishTitle[i],
                japaneseTitle = dataJapaneseTitle[i],
                synopsis = dataSynopsis[i],
                episodes = dataEpisodes[i].toInt(),
                type = dataType[i],
                rated = dataRated[i].toFloat(),
                genres = dataGenres[i],
                studios = dataStudios[i],
                status = dataStatus[i],
                picture = dataPicture[i],
                animeCover = dataAnimeCover[i],
                characters = dataCharacters[i]
            )
            listAnime.add(anime)
        }
        return listAnime
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}