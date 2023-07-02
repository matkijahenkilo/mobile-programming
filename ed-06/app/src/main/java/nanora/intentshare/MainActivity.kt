package nanora.intentshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun share(view: View) {
        val clickedButton: TextView = findViewById(view.id)

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, clickedButton.text)
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        startActivity(shareIntent)
    }
}