package com.example.tenantmanagementsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.getString("savedName")?.let { name ->
            binding.tenant = Tenant(
                name,
                savedInstanceState.getString("savedPhone", ""),
                savedInstanceState.getString("savedRent", "")
            )
        }

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            if (name.isEmpty()) {
                binding.tenantNameEditText.error = getString(R.string.name_required)
                binding.tenantNameEditText.requestFocus()
                return@setOnClickListener
            }

            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()
            binding.tenant = Tenant(name, phone, rent)
            binding.tenantNameEditText.error = null
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
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
