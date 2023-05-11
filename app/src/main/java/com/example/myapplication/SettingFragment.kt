import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.HomeFragment
import com.example.myapplication.LoginActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDahsBinding
import com.example.myapplication.databinding.FragmentAddBinding
import com.example.myapplication.databinding.FragmentSettingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
   private lateinit var bond :ActivityDahsBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        // Find the logout button from the layout file
        val logoutButton = view.findViewById<Button>(R.id.btn_logout)

        // Set an OnClickListener to the logout button
        logoutButton.setOnClickListener {
            // Clear the saved username and password from SharedPreferences
            val sharedPreferences = requireContext().getSharedPreferences("MyPreference", MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()

            // Go back to the login screen
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view


        val name:String = binding.Peronalname.text.toString()
        val dep:String =binding.Branch.text.toString()
if (name.isNotEmpty())
{

}

    }

}
