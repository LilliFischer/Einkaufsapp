package com.example.einkaufsapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputArtikel: EditText
    private lateinit var buttonHinzufuegen: Button
    private lateinit var listViewArtikel: ListView

    private val artikelListe = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputArtikel = findViewById(R.id.inputArtikel)
        buttonHinzufuegen = findViewById(R.id.buttonHinzufuegen)
        listViewArtikel = findViewById(R.id.listViewArtikel)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, artikelListe)
        listViewArtikel.adapter = adapter

        buttonHinzufuegen.setOnClickListener {
            val artikel = inputArtikel.text.toString()
            if (artikel.isNotBlank()) {
                artikelListe.add(artikel)
                adapter.notifyDataSetChanged()
                inputArtikel.text.clear()
            }
        }

        listViewArtikel.setOnItemClickListener { _, _, position, _ ->
            artikelListe.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
}
