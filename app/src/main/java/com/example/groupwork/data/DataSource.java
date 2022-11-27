package com.example.groupwork.data;

import com.example.groupwork.StickerActivity.Sticker;

import java.util.ArrayList;

public class DataSource {

    public ArrayList<Sticker> loadStickers() {

        ArrayList<Sticker> stickersList = new ArrayList<Sticker>();
        stickersList.add(new Sticker("sticker_barbarian"));
        stickersList.add(new Sticker("sticker_bard"));
        stickersList.add(new Sticker("sticker_bloodhunter"));
        stickersList.add(new Sticker("sticker_cleric"));
        stickersList.add(new Sticker("sticker_crit_fail_cha"));
        stickersList.add(new Sticker("sticker_crit_fail_con"));
        stickersList.add(new Sticker("sticker_crit_fail_dex"));
        stickersList.add(new Sticker("sticker_crit_fail_int"));
        stickersList.add(new Sticker("sticker_crit_fail_str"));
        stickersList.add(new Sticker("sticker_crit_fail_wis"));
        stickersList.add(new Sticker("sticker_crit_hit"));
        stickersList.add(new Sticker("sticker_crit_success_cha"));
        stickersList.add(new Sticker("sticker_crit_success_con"));
        stickersList.add(new Sticker("sticker_crit_success_dex"));
        stickersList.add(new Sticker("sticker_crit_success_int"));
        stickersList.add(new Sticker("sticker_crit_success_str"));
        stickersList.add(new Sticker("sticker_crit_success_wis"));
        stickersList.add(new Sticker("sticker_d10"));
        stickersList.add(new Sticker("sticker_d10_die_emote"));
        stickersList.add(new Sticker("sticker_d12"));
        stickersList.add(new Sticker("sticker_d12_die_emote"));
        stickersList.add(new Sticker("sticker_d20"));
        stickersList.add(new Sticker("sticker_d20_die_emote"));
        stickersList.add(new Sticker("sticker_d4"));
        stickersList.add(new Sticker("sticker_d4_die_emote"));
        stickersList.add(new Sticker("sticker_d6"));
        stickersList.add(new Sticker("sticker_d6_die_emote"));
        stickersList.add(new Sticker("sticker_d8"));
        stickersList.add(new Sticker("sticker_d8_die_emote"));
        stickersList.add(new Sticker("sticker_dnd_and"));
        stickersList.add(new Sticker("sticker_dnd_logo"));
        stickersList.add(new Sticker("sticker_druid"));
        stickersList.add(new Sticker("sticker_fighter"));
        stickersList.add(new Sticker("sticker_magicwand"));
        stickersList.add(new Sticker("sticker_monk"));
        stickersList.add(new Sticker("sticker_nolens_dog"));
        stickersList.add(new Sticker("sticker_paladin"));
        stickersList.add(new Sticker("sticker_ranger"));
        stickersList.add(new Sticker("sticker_rogue"));
        stickersList.add(new Sticker("sticker_sorcerer"));
        stickersList.add(new Sticker("sticker_wizard"));
        stickersList.add(new Sticker("sticker_swords_dnd_sword30"));
        stickersList.add(new Sticker("sticker_warlock"));

        return stickersList;
    }

}
