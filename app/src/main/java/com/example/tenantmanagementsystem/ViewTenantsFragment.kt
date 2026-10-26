package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystem.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {
    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!
    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onCreateView")
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onViewCreated")
        val newTenant = args.newTenant
        binding.tenant = newTenant
        binding.newTenantGroup.visibility = if (newTenant == null) View.GONE else View.VISIBLE
        binding.callButton.setOnClickListener {
            if (newTenant != null) {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", newTenant.phone, null)))
            }
        }
        binding.shareButton.setOnClickListener {
            if (newTenant != null) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, newTenant.summary())
                startActivity(Intent.createChooser(intent, "Share tenant"))
            }
        }
        val tenants = TenantRepository.tenants
        binding.allTenantsTextView.text = if (tenants.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            tenants.joinToString("\n\n") { it.summary() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onDestroyView")
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "   ViewTenantsFragment: onDestroy")
    }
}
