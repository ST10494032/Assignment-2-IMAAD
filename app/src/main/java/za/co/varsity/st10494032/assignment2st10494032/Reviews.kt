package za.co.varsity.st10494032.assignment2st10494032

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Reviews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reviews)

        // Display all questions with correct answers
        val reviewText = findViewById<TextView>(R.id.reviewText)
        val reviewContent = """
            1. The Great Wall of China was built during the Ming Dynasty. - True
            2. The Declaration of Independence was signed in 1776. - True
            3. Napoleon was defeated at the Battle of Hastings. - False
            4. World War I ended in 1920. - False
            5. The Roman Empire fell in 476 AD. - True
        """.trimIndent()
        reviewText.text = reviewContent

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}