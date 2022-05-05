package com.example.myfirstapplication.addchild


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.myfirstapplication.MyAppDataBase
import com.example.myfirstapplication.R
import com.example.myfirstapplication.databinding.FragmentAddChildBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddChildFragment : Fragment() {


    private lateinit var binding: FragmentAddChildBinding
   private val arg: AddChildFragmentArgs by navArgs()
    private val viewModel: AddChildViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_child,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        if(arg.childDataArg != null)
        {
            arg.childDataArg?.let { viewModel.id = it.id }
            viewModel.edChildName.value = arg.childDataArg?.childName
            viewModel.edChildAge.value = arg.childDataArg?.childAge
            viewModel.setChildImageUri(arg.childDataArg?.childImageUrl)
            viewModel.setChildManageOption(arg.childDataArg?.childManageOption)

            binding.kidImageInAddChild.load(arg.childDataArg?.childImageUrl)

            if(arg.childDataArg?.childManageOption == getString(R.string.monitor_and_manage_text))
            {
                binding.monitorAndManageOptionInAddChild.isChecked = true
            }
            else{
                binding.monitorOptionInAddChild.isChecked = true
            }

            Toast.makeText(binding.root.context, "checkArg value ${arg.childDataArg!!.id}", Toast.LENGTH_SHORT).show()
        }
        else{
            viewModel.setChildManageOption(getString(R.string.monitor_and_manage_text))
           // viewModel.setChildImageUri("https://flyclipart.com/thumb2/child-boy-clipart-collection-889164.png")
            Toast.makeText(binding.root.context, "checkArg null", Toast.LENGTH_SHORT).show()
        }


        binding.doneButtonInAddChild.setOnClickListener {

            Log.i("insert","button click")
            CoroutineScope(Dispatchers.IO).launch{
                if (arg.childDataArg != null)
                {
                    Log.i("insert","fragment")
                   viewModel.updateChildData()
                }
                else{

                   viewModel.insertChildData()
                }

            }
            findNavController().navigateUp()
        }


        val contentResolver = binding.root.context.contentResolver

        val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        // Check for the freshest data.

        val getImage = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) {

            contentResolver.takePersistableUriPermission(it, takeFlags)
            viewModel.setChildImageUri(it.toString())
            Log.i("imagetest","$it")
            // Toast.makeText(binding.root.context, "${viewModel.uri}", Toast.LENGTH_SHORT).show()
            binding.kidImageInAddChild.load(it)
        }

        binding.imageFloatingButtonInAddChild.setOnClickListener{
           getImage.launch(arrayOf("image/*"))
        }


        binding.radioGroupInAddChild.setOnCheckedChangeListener { _, id ->
            if (id == R.id.monitor_option_in_addChild) {
                Log.i("Radio", "monitor")
                viewModel.setChildManageOption(getString(R.string.monitor_only_text))
            } else if (id == R.id.monitor_and_manage_option_in_addChild) {
                Log.i("Radio", "monitor and manage")
                viewModel.setChildManageOption(getString(R.string.monitor_and_manage_text))
            }

        }


        return binding.root
    }





}