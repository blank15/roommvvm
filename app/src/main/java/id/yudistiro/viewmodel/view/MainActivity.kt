package id.yudistiro.viewmodel.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.yudistiro.viewmodel.R
import id.yudistiro.viewmodel.adapter.AdapterList
import id.yudistiro.viewmodel.data.Word
import id.yudistiro.viewmodel.data.WordDao
import id.yudistiro.viewmodel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var  model: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = AdapterList(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        model.allword.observe(this, Observer {
                words ->

            Log.d("isi",words.size.toString())
            words?.let {
                adapter.setWords(words)
            }
        }
        )


        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWord::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val word = Word(it.getStringExtra(NewWord.EXTRA_REPLY))
                model.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Tidak ada yang disimpan",
                Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val newWordActivityRequestCode = 1
    }
}
