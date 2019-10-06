package fragments


import adapters.Article
import adapters.ArticleAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsletter_app.R
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import networks.ArticleRepository

class ArticlesFragment : Fragment() {

    private val repository = ArticleRepository()
    private val recyclerView by lazy { recycler }
    private val spinner by lazy {spinner_frag}
    /**
     * Ici, on associe le layout à afficher dans le fragment */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_articles,
            container, false
        )

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GlobalScope.launch {
            getData()
        }
    }


    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.list()
            bindData(result)
        }
    }

    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, result)
            spinner.adapter = adapter
            var adapterRecycler = ArticleAdapter(result)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapterRecycler
        }
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context, "Vous n'avez rien selectionné", Toast.LENGTH_LONG)
                    .show()
            }
            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {}
        }

    }*/


}


