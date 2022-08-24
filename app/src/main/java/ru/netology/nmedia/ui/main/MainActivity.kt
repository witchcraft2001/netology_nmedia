package ru.netology.nmedia.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.data.entities.Post
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.ui.edit.EditPostActivity
import ru.netology.nmedia.utils.NumbersFormatter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val postContract = registerForActivityResult(EditPostActivity.Contract) { post ->
        if (post != null) {
            viewModel.save(post)
            Toast.makeText(this, getString(R.string.post_saved), Toast.LENGTH_LONG).show()
        }
    }

    private lateinit var binding: ActivityMainBinding
    private val numbersFormatter = NumbersFormatter()
    private val adapter = PostAdapter(
        numbersFormatter = numbersFormatter,
        onLikeListener = { id ->
            viewModel.like(id)
        },
        onShareListener = { id ->
            viewModel.share(id)
        },
        onRemoveListener = { id ->
            viewModel.remove(id)
        },
        onEditListener = { post ->
            postContract.launch(post)
//            viewModel.edit(post)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            recyclerView.swapAdapter(adapter, false)
//            editText.addTextChangedListener { editable -> viewModel.updateText(editable.toString()) }
//            buttonSavePost.setOnClickListener { viewModel.save() }
//            buttonCancel.setOnClickListener { viewModel.cancelEdit() }
            addButton.setOnClickListener { postContract.launch(Post.getEmpty()) }
        }
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
//        viewModel.sourceMessage.observe(this) { message ->
//            with(binding) {
//                groupEdit.visibility = if (message.isNullOrEmpty()) View.GONE else View.VISIBLE
//                textEditMessage.text = message
//            }
//        }
//        viewModel.post.observe(this) { post ->
//            with(binding) {
//                if (editText.text.toString() != post?.text) {
//                    editText.setText(post?.text)
//                }
//            }
//        }

    }

    override fun onDestroy() {
        binding.recyclerView.swapAdapter(null, false)
        super.onDestroy()
    }
}