package com.example.groupwork.DNDChat;

public class ClickableChat {
    String campaignName;

    public ClickableChat (String campaignName){
        this.campaignName = campaignName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setChatName(String campaignName) {
        this.campaignName = campaignName;
    }

    @Override
    public String toString() {
        return campaignName;
    }
}
