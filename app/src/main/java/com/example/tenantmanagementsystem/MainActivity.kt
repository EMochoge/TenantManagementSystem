package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        binding.saveButton.setOnClickListener {
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
        binding.tenant?.let { tenant ->
            outState.putString("savedName", tenant.name)
            outState.putString("savedPhone", tenant.phone)
            outState.putString("savedRent", tenant.rent)
        }
        super.onSaveInstanceState(outState)
    }
}
