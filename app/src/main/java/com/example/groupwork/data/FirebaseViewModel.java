package com.example.groupwork.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.groupwork.Sticker;
import com.example.groupwork.StickerMessage;

import java.util.List;

public class FirebaseViewModel extends ViewModel {

    // Expose screen UI state
    private MutableLiveData<List<Sticker>> selectedStickers;
    private MutableLiveData<List<Sticker>> allStickers;

    public LiveData<List<Sticker>> getSelectedStickers() {
        if (selectedStickers == null) {
            selectedStickers = new MutableLiveData<List<Sticker>>();
            loadSelectedStickers();
        }
        return selectedStickers;
    }

    // Handle business logic
    private void loadSelectedStickers() {
        // Do an asynchronous operation to fetch users.
    }

    public void selectStickers(List<Sticker> sl) {
        selectedStickers.setValue(sl);
//        selectedItem.setValue(item);
    }

    public LiveData<List<Sticker>> getStickers() {
        if (allStickers == null) {
            allStickers = new MutableLiveData<List<Sticker>>();
            loadAllStickers();
        }
        return allStickers;
    }

    // Handle business logic
    private void loadAllStickers() {
        // Do an asynchronous operation to fetch users.
    }

    public void initStickers(List<Sticker> sl) {
        if (allStickers == null) {
            allStickers = new MutableLiveData<List<Sticker>>();
            allStickers.setValue(sl);
        }
    }

    public void setStickersCount(List<Sticker> sl) {
        initStickers(sl);
        for(int i = 0; i < sl.size(); i++){
            allStickers.getValue().get(i).setNumUse(sl.get(i).getNumUse());
        }
        //selectedStickers.setValue(sl);
//        selectedItem.setValue(item);
    }
}
