package za.co.varsity.st10494032.assignment2st10494032

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Scores : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scores)

        // Retrieve the score from the intent
        val score = intent.getIntExtra("score", 0)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val resultText = findViewById<TextView>(R.id.resultText)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        // Display the user's score
        scoreText.text = "You got $score out of 5 correct"

        // Give personalized feedback based on the score
        resultText.text = when (score) {
            5 -> "Excellent work!"
            in 3..4 -> "Good job! Keep trying"
            else -> "Don't worry, try again"
        }

        // Set up button to review answers
        reviewButton.setOnClickListener {
            val intent = Intent (this, Reviews::class.java)
            startActivity(intent)
        }

        // Set up button to exit the app
        exitButton.setOnClickListener {
            finishAffinity() // Exit the app completely
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}