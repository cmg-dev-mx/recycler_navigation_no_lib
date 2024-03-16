package mx.dev.shellcore.android.recyclernavigation.list.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.dev.shellcore.android.recyclernavigation.R
import mx.dev.shellcore.android.recyclernavigation.core.model.Info
import mx.dev.shellcore.android.recyclernavigation.databinding.FragmentListBinding
import mx.dev.shellcore.android.recyclernavigation.list.adapter.ListAdapter

class ListFragment : Fragment() {

    companion object {
        private var _instance: ListFragment? = null

        fun getInstance(onClickListener: (Info) -> Unit): ListFragment {
            if (_instance == null) {
                _instance = ListFragment().apply {
                    this.onClickListener = onClickListener
                }
            }
            return _instance!!
        }
    }

    private var onClickListener: (Info) -> Unit = {}

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private val list = listOf(
        Info("Title 1", "Description 1"),
        Info("Title 2", "Description 2"),
        Info("Title 3", "Description 3"),
        Info("Title 4", "Description 4"),
        Info("Title 5", "Description 5"),
        Info("Title 6", "Description 6"),
        Info("Title 7", "Description 7"),
        Info("Title 8", "Description 8"),
        Info("Title 9", "Description 9"),
        Info("Title 10", "Description 10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.fragmentListRec.apply {
            this.layoutManager = LinearLayoutManager(context)
            if (this.itemDecorationCount > 0) {
                this.removeItemDecorationAt(0)
            }
            this.addItemDecoration(object: RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val position = parent.getChildAdapterPosition(view)
                    val space = resources.getDimensionPixelSize(R.dimen.space)
                    outRect.set(space, space, space, if (position == state.itemCount - 1) space else 0)
                }
            })
            this.setHasFixedSize(true)
            adapter = ListAdapter(list) {
                onClickListener(it)
            }
        }

        return binding.root
    }
}