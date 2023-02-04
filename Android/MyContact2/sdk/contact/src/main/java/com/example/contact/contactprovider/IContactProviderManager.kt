package com.example.contact.contactprovider

import android.app.Application
import com.example.contact.data.ContactProvider
import com.example.contact.data.DeviceUser

/**
 * This interface hold the structure to fetch from contact provider
 */
interface IContactProviderManager {

    /**
     * This function will get all the email address of the contact
     *
     * @param contactId: the contact that going to search emails for
     *
     * @return All the contact emails exist in the contact provider which could be empty too
     */
    suspend fun getContactEmails(application: Application, contactId: String): List<String>

    /**
     * This function will get all the phone number of the contact
     *
     * @param contactId: the contact that going to search phone number for
     *
     * @return All the contact phone number exist in the contact provider which could be empty too
     */
    suspend fun getContactPhoneNumbers(application: Application,contactId: String): List<String>

    /**
     * This function will get all the available contact in the contact provider
     *
     * @return A list of all the contact in the contact provider
     */
    suspend fun getAllContact(application: Application): List<ContactProvider>

    suspend fun getTheDeviceOwner(application: Application): DeviceUser
}