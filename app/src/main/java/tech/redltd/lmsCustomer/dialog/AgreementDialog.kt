package tech.redltd.lmsCustomer.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.activities.applyLoan.ApplyLoanActivity
import tech.redltd.lmsCustomer.utils.successToast

class AgreementDialog : DialogFragment() {
    lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.agreement_dialog_layout,container,false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val readCheckBox : CheckBox = view.findViewById(R.id.readCheckBox)
        val agreeBtn : Button = view.findViewById(R.id.confirmBtn)
        val canceleBtn:Button = view.findViewById(R.id.cancleBtn)
        agreeBtn.isEnabled = false
        readCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                agreeBtn.isEnabled = isChecked
            }
        }
        canceleBtn.setOnClickListener {
            this.dismiss()
        }
        agreeBtn.setOnClickListener {
            mContext.startActivity(Intent(mContext,ApplyLoanActivity::class.java))
        }
    }
}