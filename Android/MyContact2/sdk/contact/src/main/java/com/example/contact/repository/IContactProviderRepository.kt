package com.example.contact.repository

import android.app.Application
import com.example.contact.repository.data.Contact
import com.example.contact.repository.data.Owner

/**
 * This interface hold the structure for the contact repository
 */
interface IContactProviderRepository {

    /**
     * Call this method to get all the contact saved in the device
     */
    suspend fun getAllContactFromDevice(application: Application): List<Contact>

    /**
     * Call this method to get the user profile
     */
    suspend fun getTheDeviceOwnerProfile(application: Application): Owner
}