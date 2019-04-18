package com.kajornsak.smartreply

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentificationOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val languageIdentification: FirebaseLanguageIdentification by lazy {
        FirebaseNaturalLanguage.getInstance().languageIdentification
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        identifyButton.setOnClickListener {
            val text = languageInput.text.toString()
            identifyLanguages(text)
        }
    }

    private fun identifyLanguages(text: String) {
        val options = FirebaseLanguageIdentificationOptions.Builder().setConfidenceThreshold(0.5f).build()
        val languageIdentification = FirebaseNaturalLanguage.getInstance().getLanguageIdentification(options)


        langIden
            .identifyPossibleLanguages(text)
            .addOnSuccessListener {
                var output = ""
                it.map {
                    output += "${it.languageCode} : ${it.confidence} \n"
                }
                resultTextView.text = output
            }
            .addOnFailureListener {
                Log.e("IDENTIFICATION ERROR", it.localizedMessage)
            }
    }
}


/*
*  private fun identifyLanguage(text: String) {
        languageIdentification
            .identifyLanguage(text)
            .addOnSuccessListener {
                resultTextView.text = "It's $it"
            }
            .addOnFailureListener {
                resultTextView.text = it.localizedMessage
            }
    }

* */