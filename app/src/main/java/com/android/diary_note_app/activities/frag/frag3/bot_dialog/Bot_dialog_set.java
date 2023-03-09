package com.android.diary_note_app.activities.frag.frag3.bot_dialog;

public class Bot_dialog_set{

    public Bot_dialog_set(){
        OrderBottomDialogFragment orderBottomDialogFragment = new OrderBottomDialogFragment(dayDiaryItem);
        orderBottomDialogFragment.show(getParentFragmentManager(), orderBottomDialogFragment.getTag());

    }

}
