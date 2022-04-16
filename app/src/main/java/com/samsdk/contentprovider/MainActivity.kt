package com.samsdk.contentprovider

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samsdk.contentprovider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        var rs = contentResolver.query(
            AcronymProvider.CONTENT_URI, arrayOf(
                AcronymProvider._ID, AcronymProvider.NAME, AcronymProvider.MEANING
            ), null, null, AcronymProvider.NAME
        )
        binding.apply {
            btnNext.setOnClickListener {
                if (rs?.moveToNext()!!) {
                    binding.editName.setText(rs.getString(1))
                    binding.editMean.setText(rs.getString(2))
                }
            }

            btnPre.setOnClickListener {
                if (rs?.moveToPrevious()!!) {
                    binding.editName.setText(rs.getString(1))
                    binding.editMean.setText(rs.getString(2))
                }
            }

            btnInsert.setOnClickListener {
                val cv = ContentValues()
                cv.put(AcronymProvider.NAME, editName.text.toString().trim())
                cv.put(AcronymProvider.MEANING, editMean.text.toString().trim())
                contentResolver.insert(AcronymProvider.CONTENT_URI, cv)
                rs?.requery()
            }

            btnUpdate.setOnClickListener {
                val cv = ContentValues()
                cv.put(AcronymProvider.MEANING, editMean.text.toString().trim())
                contentResolver?.update(
                    AcronymProvider.CONTENT_URI,
                    cv,
                    "NAME = ?",
                    arrayOf(editName.text.toString().trim())
                )
                rs?.requery()
            }

            btnDelete.setOnClickListener {
                contentResolver?.delete(
                    AcronymProvider.CONTENT_URI,
                    "NAME = ?",
                    arrayOf(editName.text.toString().trim())
                )
                rs?.requery()
            }

            btnClear.setOnClickListener {
                editMean.text?.clear()
                editName.text?.clear()
                editName.requestFocus()
            }
        }
    }
}