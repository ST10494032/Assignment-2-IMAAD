package za.co.varsity.st10494032.assignment2st10494032

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizQuestions : AppCompatActivity() {

    // List of flashcard questions and their correct answers
    private  val  questions = listOf(
        Pair ("The Great Wall of China was built during the Ming Dynasty.", true),
        Pair("The Declaration of Independence was signed in 1776.", true),
        Pair("Napoleon was defeated at the Battle of Hastings.", false),
        Pair("World War I ended in 1920.", false),
        Pair("The Roman Empire fell in 476 AD.", true)
    )
    private  var currentQuestionIndex = 0
    private var score = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)

        // Get references to UI components
        val questionText = findViewById<TextView>(R.id.questionText)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Function to load the current question
        fun loadQuestion() {
            if (currentQuestionIndex < questions.size) {
                val question = questions[currentQuestionIndex].first
                questionText.text = question
                feedbackText.text = ""
            }
        }

        fun disableAnswerButtons() {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        fun enableAnswersButton() {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }

        // Handle True button click
        trueButton.setOnClickListener {
            checkAnswer(true, feedbackText)
        }

        // Handle False button click
        falseButton.setOnClickListener {
            checkAnswer(false, feedbackText)
        }

        // Handle Next button click
        nextButton.setOnClickListener {

            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion()
            } else {

                // All questions done, move to score screen
                val intent = Intent(this, Scores::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }

        // Load the first question
        loadQuestion()
    }

    // Check the user's answer and provide feedback
    private fun  checkAnswer(userAnswer: Boolean, feedbackText: TextView) {
        val correctAnswer = questions[currentQuestionIndex].second
        if (userAnswer == correctAnswer) {
            score++
            feedbackText.text = "Correct!"
        } else {
            feedbackText.text = "Incorrect"
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}