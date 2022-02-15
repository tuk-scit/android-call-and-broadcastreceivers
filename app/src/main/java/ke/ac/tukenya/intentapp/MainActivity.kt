package ke.ac.tukenya.intentapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var tvPhone: EditText

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvPhone = findViewById(R.id.etPhone)
    }

    fun goToProfiles(view: View) {
        // Explicit Intent
        val profileIntent = Intent(this,
            ProfileActivity::class.java)
        profileIntent.putExtra("name", "Felix Okoth")
        startActivity(profileIntent)
    }

    fun makeACall(view: View) {
        // factor security.
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED -> {
                    call()
                }
            shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {
                Snackbar.make(view, "Please, please just accept! Please!",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Accept", View.OnClickListener {
                        // Request permission.
                        requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),
                            0)
                    })
            } else -> {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),
                    0)
            }
        }
    }

    private fun call() {
        // Implicit inter.
        val phoneNo = this.tvPhone.text
        val call = Intent(Intent.ACTION_CALL)
        call.setData(Uri.parse("tel:$phoneNo"))
        startActivity(call)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call()
            } else {
                Log.w(TAG, "onRequestPermissionsResult: Call phone permission rejected")
            }
        }
    }
}