package ru.netology.nmedia.ui.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ru.netology.nmedia.data.entities.Post
import ru.netology.nmedia.databinding.ActivityEditPostBinding

class EditPostActivity : AppCompatActivity() {
    companion object {
        private const val ARG_POST = "ARG_POST"
    }

    private val viewModel: EditPostViewModel by viewModels()
    private lateinit var binding: ActivityEditPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getParcelableExtra<Post>(ARG_POST)?.let { viewModel.setPost(it) }
        with(binding) {
            editText.addTextChangedListener { editable -> viewModel.updateText(editable.toString()) }
            okButton.setOnClickListener {
                setResult(
                    if (viewModel.post.value?.text.isNullOrEmpty()) RESULT_CANCELED else RESULT_OK,
                    Intent().apply { putExtra(ARG_POST, viewModel.post.value) })
                finish()
            }
            cancelButton.setOnClickListener {
                finish()
            }
        }
        viewModel.sourceMessage.observe(this) { message ->
            with(binding) {
                groupEdit.visibility = if (message.isNullOrEmpty()) View.GONE else View.VISIBLE
                textEditMessage.text = message
            }
        }

        viewModel.post.observe(this) { post ->
            with(binding) {
                if (editText.text.toString() != post?.text) {
                    editText.setText(post?.text)
                }
            }
        }
    }

    object Contract : ActivityResultContract<Post, Post?>() {
        override fun createIntent(context: Context, input: Post) = Intent(context, EditPostActivity::class.java).apply {
            putExtra(ARG_POST, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?) =
            if (resultCode == Activity.RESULT_OK) {
                intent?.getParcelableExtra<Post>(ARG_POST)
            } else {
                null
            }
    }
}