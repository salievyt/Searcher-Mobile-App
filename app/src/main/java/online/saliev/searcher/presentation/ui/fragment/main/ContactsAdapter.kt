package online.saliev.searcher.presentation.ui.fragment.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import online.saliev.searcher.data.api.model.Contact
import online.saliev.searcher.databinding.ContactsBinding
import online.saliev.searcher.viewmodel.Links

class ContactAdapter(private var contacts: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateContacts(newContacts: List<Contact>) {
        this.contacts = newContacts
        notifyDataSetChanged()
    }

    class ContactViewHolder(binding: ContactsBinding) : ViewHolder(binding.root) {
        val nameTextView: TextView = binding.contactName
        val numberTextView: TextView = binding.contactNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactViewHolder (
        ContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contacts[position]
        holder.nameTextView.text = currentContact.name
        holder.numberTextView.text = currentContact.phoneNumber
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(Links().searchWhatsapp(currentContact.phoneNumber)))
            startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}
