package com.example.contact.contactprovider

import android.app.Application
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import com.example.contact.ContactConstant
import com.example.contact.data.ContactProvider
import com.example.contact.data.DeviceUser

internal class ContactProviderManager : IContactProviderManager {


    override suspend fun getContactEmails(
        application: Application, contactId: String
    ): List<String> {
        val emails = mutableListOf<String>()
        Log.i(
            CLASS_NAME,
            "${ContactConstant.CONTACT_PROVIDER_TAG} getContactEmails of contactId: $contactId"
        )

        val cursor = createCursor(
            application,
            arrayOf(
                ContactsContract.CommonDataKinds.Email.CONTACT_ID,
                ContactsContract.CommonDataKinds.Email.ADDRESS
            ),
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            "${ContactsContract.CommonDataKinds.Email.CONTACT_ID} = ?",
            arrayOf(contactId)
        )
        while (cursor != null && cursor.count > 0) {
            if (cursor.moveToNext()) {
                val contactIdIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID)
                val contactEmailAddressIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)

                val contactIdValue = cursor.getString(contactIdIndex)
                val emailAddress = cursor.getString(contactEmailAddressIndex)
                Log.i(
                    CLASS_NAME,
                    "${ContactConstant.CONTACT_PROVIDER_TAG} getContactEmails " +
                            "in cursor loop  contactId: $contactIdValue, email: $emailAddress"
                )
                emails.add(emailAddress)
            }
        }
        Log.i(
            CLASS_NAME,
            "${ContactConstant.CONTACT_PROVIDER_TAG} getContactEmails final email list: $emails"
        )
        return emails
    }

    override suspend fun getContactPhoneNumbers(
        application: Application, contactId: String
    ): List<String> {
        val phoneNumbers = mutableListOf<String>()
        Log.i(
            CLASS_NAME,
            "${ContactConstant.CONTACT_PROVIDER_TAG} getContactPhoneNumbers of contactId: $contactId"
        )
        val cursor = createCursor(
            application,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
            arrayOf(contactId)
        )
        while (cursor != null && cursor.count > 0) {
            if (cursor.moveToNext()) {
                val contactIdIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val contactPhoneNumberIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val contactIdValue = cursor.getString(contactIdIndex)
                val phoneNumber = cursor.getString(contactPhoneNumberIndex)
                Log.i(
                    CLASS_NAME,
                    "${ContactConstant.CONTACT_PROVIDER_TAG} getContactPhoneNumbers " +
                            "in cursor loop  contactId: $contactIdValue, phoneNumber: $phoneNumber"
                )
                phoneNumbers.add(phoneNumber)

            }
        }
        Log.i(
            CLASS_NAME,
            "${ContactConstant.CONTACT_PROVIDER_TAG} getContactPhoneNumbers final phoneNumber" +
                    " list: $phoneNumbers"
        )
        return phoneNumbers
    }

    override suspend fun getAllContact(application: Application): List<ContactProvider> {
        val contacts = mutableListOf<ContactProvider>()
        Log.i(
            CLASS_NAME, "${ContactConstant.CONTACT_PROVIDER_TAG} getAllContact"
        )
        val cursor = createCursor(
            application,
            arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI
            ),
            ContactsContract.Contacts.CONTENT_URI
        )
        while (cursor != null && cursor.count > 0) {
            if (cursor.moveToNext()) {
                val cursorIdIndex =
                    cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val cursorNameIndex =
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val cursorPhotoUriIndex =
                    cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)
                val contactProvider = ContactProvider(
                    contactId = cursor.getString(cursorIdIndex),
                    name = cursor.getString(cursorNameIndex),
                    photoUri = cursor.getString(cursorPhotoUriIndex)
                )
                Log.i(
                    CLASS_NAME,
                    "${ContactConstant.CONTACT_PROVIDER_TAG} getAllContact " +
                            "in cursor loop  data: $contactProvider"
                )
                contacts.add(contactProvider)
            }
        }
        Log.i(
            CLASS_NAME,
            "${ContactConstant.CONTACT_PROVIDER_TAG} getAllContact final contacts" +
                    " list: $contacts"
        )
        return contacts
    }

    override suspend fun getTheDeviceOwner(application: Application): DeviceUser {
        val owner = DeviceUser()
        Log.i(
            CLASS_NAME, "${ContactConstant.CONTACT_PROVIDER_TAG} getTheDeviceOwner"
        )
        val cursor = createCursor(
            application,
            arrayOf(
                ContactsContract.Profile._ID,
                ContactsContract.Profile.DISPLAY_NAME,
                ContactsContract.Profile.LOOKUP_KEY,
                ContactsContract.Profile.PHOTO_THUMBNAIL_URI,
                ContactsContract.Profile.PHOTO_URI
            ),
            ContactsContract.Profile.CONTENT_URI
        )
        while (cursor != null && cursor.count > 0){
            if (cursor.moveToNext()){
                val profileIdIndex =
                    cursor.getColumnIndex(ContactsContract.Profile._ID)
                val profileNameIndex =
                    cursor.getColumnIndex(ContactsContract.Profile.DISPLAY_NAME)
                val lookupKeyIndex =
                    cursor.getColumnIndex(ContactsContract.Profile.LOOKUP_KEY)
                val profileThumbnailIndex =
                    cursor.getColumnIndex(ContactsContract.Profile.PHOTO_THUMBNAIL_URI)
                val profilePhotoIndex =
                    cursor.getColumnIndex(ContactsContract.Profile.PHOTO_URI)
                owner.id = cursor.getString(profileIdIndex)
                owner.name = cursor.getColumnName(profileNameIndex)
                owner.lookUpKey = cursor.getString(lookupKeyIndex)
                owner.thumbnailPhotoUri = cursor.getString(profileThumbnailIndex)
                owner.photoUri = cursor.getString(profilePhotoIndex)
                Log.i(
                    CLASS_NAME,
                    "${ContactConstant.CONTACT_PROVIDER_TAG} getTheDeviceOwner " +
                            "in cursor loop  data: $owner"
                )
            }
        }
        Log.i(
            CLASS_NAME,
            "${ContactConstant.CONTACT_PROVIDER_TAG} getTheDeviceOwner final profile" +
                    ": $owner"
        )
        return owner
    }

    private fun createCursor(
        application: Application,
        projection: Array<String>,
        uri: Uri,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
        sortOrder: String? = null
    ): Cursor? {
        return application.contentResolver.query(
            uri, projection, selection, selectionArgs, sortOrder
        )
    }

    companion object {
        private const val CLASS_NAME = "ContactProviderManager"
    }
}