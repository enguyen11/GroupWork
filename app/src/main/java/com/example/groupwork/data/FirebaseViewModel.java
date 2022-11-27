package com.example.groupwork.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirebaseViewModel extends ViewModel {

    // Expose screen UI state
    private MutableLiveData<String> user;

    public LiveData<String> getCurrentUser() {
        if (user == null) {
            user = new MutableLiveData<String>();
            loadUser();
        }
        return user;
    }

    // Handle business logic
    private void loadUser() {
        // Do an asynchronous operation to fetch users.
    }

    public void setUser(String s) {
        user.setValue(s);
//        selectedItem.setValue(item);
    }


}
