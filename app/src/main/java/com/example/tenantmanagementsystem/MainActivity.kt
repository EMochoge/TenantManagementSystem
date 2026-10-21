package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var saveCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("EMAIL")
        if (savedInstanceState == null && email != null) {
            Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
        }

        savedInstanceState?.getString("savedName")?.let { name ->
            binding.tenant = Tenant(
                name,
                savedInstanceState.getString("savedPhone", ""),
                savedInstanceState.getString("savedRent", "")
            )
        }

        saveCount = savedInstanceState?.getInt("save_count", 0) ?: 0
        if (savedInstanceState != null) {
            Toast.makeText(this, "SAVE tapped $saveCount times", Toast.LENGTH_SHORT).show()
        }

        binding.saveButton.setOnClickListener {
            saveCount++
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            binding.tenantNameEditText.error = if (name.isEmpty()) "Required" else null
            binding.phoneEditText.error = if (phone.isEmpty()) "Required" else null
            binding.rentEditText.error = if (rent.isEmpty()) "Required" else null
            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
                return@setOnClickListener
            }

            binding.tenant = Tenant(name, phone, rent)
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }

        binding.callButton.setOnClickListener {
            val tenant = binding.tenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tenant.phone, null))
            startActivity(intent)
        }

        binding.shareButton.setOnClickListener {
            val tenant = binding.tenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, tenant.summary())
            startActivity(Intent.createChooser(intent, "Share tenant"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TENANT_LIFECYCLE", "MainActivity: onSaveInstanceState")
        outState.putInt("save_count", saveCount)
        binding.tenant?.let { tenant ->
            outState.putString("savedName", tenant.name)
            outState.putString("savedPhone", tenant.phone)
            outState.putString("savedRent", tenant.rent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }
}
