package ke.ac.tukenya.intentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fromMain = intent
        val name = fromMain.getStringExtra("name")

        val tvName: TextView = findViewById(R.id.tvName)
        tvName.text = "Welcome: $name"
    }

    // Explicit intent.
    fun moveToMain(view: View) {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }
}