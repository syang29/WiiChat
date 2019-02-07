package com.example.android.wiichat;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatUserAdapter extends ArrayAdapter<ChatUser>
{
    private Context mContext;
    private List<ChatUser> chatRoom = new ArrayList<ChatUser>();

    public ChatUserAdapter(@NonNull Context context, ArrayList<ChatUser> list) {
        super(context, 0 , list);
        mContext = context;
        chatRoom = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);

        ChatUser currentMessage = chatRoom.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.anonpic1);
        image.setImageResource(currentMessage.getChatPicture());

        TextView name = (TextView) listItem.findViewById(R.id.chatName);
        name.setText(currentMessage.getChatName());

        TextView message = (TextView) listItem.findViewById(R.id.chatView);
        message.setText(currentMessage.getChatMessage());

        return listItem;
    }
}
