package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var welcomeTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var activitiesSummaryTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        // Inicializar vistas
        welcomeTextView = findViewById(R.id.welcomeTextView)
        dateTextView = findViewById(R.id.dateTextView)
        activitiesSummaryTextView = findViewById(R.id.activitiesSummaryTextView)
        logoutButton = findViewById(R.id.logoutButton)

        // Configurar la interfaz
        setupUI()

        // Configurar el botón de cierre de sesión
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun setupUI() {
        // Configurar el saludo
        val userName = intent.getStringExtra("USER_NAME") ?: "Usuario"
        welcomeTextView.text = "Bienvenido, $userName"

        // Configurar la fecha actual
        val dateFormat = SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", Locale("es", "ES"))
        val currentDate = dateFormat.format(Date())
        dateTextView.text = currentDate

        // Simular carga de actividades recientes
        loadRecentActivities()
    }

    private fun loadRecentActivities() {
        val activities = listOf(
            "Completaste tu tarea diaria",
            "Alcanzaste tu meta semanal",
            "Tienes 3 nuevas notificaciones"
        )

        val summaryText = activities.joinToString("\n• ", "• ")
        activitiesSummaryTextView.text = summaryText
    }

    private fun logout() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}