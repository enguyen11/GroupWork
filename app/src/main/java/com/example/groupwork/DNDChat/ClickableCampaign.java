package com.example.groupwork.DNDChat;

public class ClickableCampaign {
    String campaignName;

    public ClickableCampaign (String campaignName){
        this.campaignName = campaignName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @Override
    public String toString() {
        return campaignName;
    }
}
