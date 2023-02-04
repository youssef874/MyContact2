package com.example.contact.repository

import android.app.Application
import com.example.contact.contactprovider.IContactProviderManager
import com.example.contact.repository.data.Contact
import com.example.contact.repository.data.Owner

class ContactProviderProviderRepository(
    private val contactProviderManager: IContactProviderManager
): IContactProviderRepository {
    override suspend fun getAllContactFromDevice(application: Application): List<Contact> {
        TODO("Not yet implemented")
    }

    override suspend fun getTheDeviceOwnerProfile(application: Application): Owner {
        TODO("Not yet implemented")
    }
}