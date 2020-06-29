package tech.redltd.lmsCustomer.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_update_load_holder_profile.*
import kotlinx.android.synthetic.main.fragment_update_load_holder_profile.nidBack
import kotlinx.android.synthetic.main.fragment_update_load_holder_profile.nidFront
import kotlinx.android.synthetic.main.fragment_update_nominee_profile.*

import tech.redltd.lmsCustomer.R
import java.io.IOException


class UpdateLoadHolderProfileFragment : Fragment() {
    lateinit var holderProfileImage: ImageView
    private val PROFILE_IMAGE = 111
    private val NID_FRONT = 222
    private val NID_BACK = 333
    private lateinit var mContext : Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_load_holder_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAutoCompleteTextView(view)
        holderProfileImage = view.findViewById(R.id.holderProfileImage)
        holderProfileImage.setOnClickListener {
            val intent = CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .getIntent(mContext)
            startActivityForResult(intent, PROFILE_IMAGE)
            //CropImage.activity().start(mContext, this)
        }

        nidFront.setOnClickListener {
            val intent = CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .getIntent(mContext)
            startActivityForResult(intent, NID_FRONT)
        }

        nidBack.setOnClickListener {
            val intent = CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .getIntent(mContext)
            startActivityForResult(intent, NID_BACK)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PROFILE_IMAGE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri: Uri = result.uri
                val selectedBitmap: Bitmap? = getCapturedImage(resultUri)
                nomineeProfileImage.setImageBitmap(selectedBitmap)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }

        if (requestCode == NID_FRONT) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri: Uri = result.uri
                val selectedBitmap: Bitmap? = getCapturedImage(resultUri)
                nidFront.setImageBitmap(selectedBitmap)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }

        if (requestCode == NID_BACK) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri: Uri = result.uri
                val selectedBitmap: Bitmap? = getCapturedImage(resultUri)
                nidBack.setImageBitmap(selectedBitmap)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }


    private fun updateAutoCompleteTextView(view: View){
        val districts = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        val districtAdapter =  ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, districts)
        val districtAutoComplete: AutoCompleteTextView = view.findViewById(R.id.districAutoComplete)
        districtAutoComplete.setAdapter(districtAdapter)
        districtAutoComplete.setText("Magura")


        val thanas = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        val thanaAdapter =  ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, thanas)
        val thanaAutoComplete: AutoCompleteTextView = view.findViewById(R.id.thanaAutoComplete)
        thanaAutoComplete.setAdapter(thanaAdapter)
    }

    private fun getCapturedImage(selectedPhotoUri: Uri): Bitmap? {
        var bitmap: Bitmap? = null
        if (Build.VERSION.SDK_INT >= 29) {
            val source: ImageDecoder.Source =  ImageDecoder.createSource( mContext.contentResolver, selectedPhotoUri)
            try {
                bitmap = ImageDecoder.decodeBitmap(source)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.contentResolver, selectedPhotoUri
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return bitmap
    }

}
