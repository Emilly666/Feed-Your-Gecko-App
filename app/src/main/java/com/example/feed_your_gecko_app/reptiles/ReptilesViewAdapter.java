package com.example.feed_your_gecko_app.reptiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.feed_your_gecko_app.AddUserReptileActivity;
import com.example.feed_your_gecko_app.database.AppDatabase;
import com.example.feed_your_gecko_app.database.relations.UserReptiles;
import com.example.feed_your_gecko_app.database.tables.UserReptile;
import com.example.feedyourgeckoapp.R;

import java.util.List;

public class ReptilesViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private List<ReptileListItem> mItemList;
    private Context context;
    private final Activity myActivity;
    AppDatabase db;

    public ReptilesViewAdapter(List<ReptileListItem> itemList, Activity activity) {
        mItemList = itemList;
        myActivity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.reptiles_list_item, parent, false);
            return new ItemViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.add_user_reptile_button, parent, false);
            return new AddButtonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) holder, position);
        } else if (holder instanceof AddButtonViewHolder) {
            addUserPlantItem((AddButtonViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }
    public int getItemViewType(int position) {
        int VIEW_TYPE_ADD_BUTTON = 1;
        return mItemList.get(position) == null ? VIEW_TYPE_ADD_BUTTON : VIEW_TYPE_ITEM;
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        Button buttonReptileItem;
        ConstraintLayout reptileDescription;
        TextView reptileName, temperature, light, humidity, vitamins, feedingFrequency;
        ImageView typeIcon, vertMenu;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonReptileItem = itemView.findViewById(R.id.buttonReptileItem);
            reptileDescription = itemView.findViewById(R.id.reptileDescription);
            reptileName = itemView.findViewById(R.id.spiecesName);
            temperature = itemView.findViewById(R.id.temperature);
            light = itemView.findViewById(R.id.light);
            humidity = itemView.findViewById(R.id.humidity);;
            vitamins = itemView.findViewById(R.id.vitamins);
            feedingFrequency = itemView.findViewById(R.id.feedingFrequency);
            vertMenu = itemView.findViewById(R.id.vertMenu);
        }
    }
    public static class AddButtonViewHolder extends RecyclerView.ViewHolder {
        Button addUserPlantButton;
        public AddButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            addUserPlantButton = itemView.findViewById(R.id.addUserReptileButton);
        }
    }
    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        UserReptiles userReptile = mItemList.get(position).userReptiles;

        viewHolder.reptileDescription.setVisibility(View.GONE);
        viewHolder.buttonReptileItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);

        viewHolder.buttonReptileItem.setOnClickListener(view -> {
            if(mItemList.get(position).isExpanded()){
                viewHolder.reptileDescription.setVisibility(View.GONE);
                viewHolder.buttonReptileItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_down_24, 0);
                mItemList.get(position).setExpanded(false);
            }else{
                viewHolder.reptileDescription.setVisibility(View.VISIBLE);
                viewHolder.buttonReptileItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_drop_up_24, 0);
                mItemList.get(position).setExpanded(true);
            }
        });

        viewHolder.buttonReptileItem.setText(userReptile.userReptile.reptileNickname);
        viewHolder.reptileName.setText(userReptile.reptile.reptileName);
        viewHolder.light.setText(userReptile.reptile.light);

        viewHolder.humidity.setText( String.format(context.getString(R.string.hum), userReptile.reptile.humidity) );
        if(userReptile.reptile.temperatureFrom == userReptile.reptile.temperatureTo){
            viewHolder.temperature.setText( String.format(context.getString(R.string.temp), userReptile.reptile.temperatureFrom) );
        }
        else{
            viewHolder.temperature.setText( String.format(context.getString(R.string.temp2), userReptile.reptile.temperatureFrom, userReptile.reptile.temperatureTo) );
        }
        viewHolder.vitamins.setText( String.format(context.getResources().getQuantityString(R.plurals.vitaminsEvery, userReptile.reptile.vitaminsFrequency, userReptile.reptile.vitaminsFrequency)) );
        viewHolder.feedingFrequency.setText( String.format(context.getResources().getQuantityString(R.plurals.feedEvery, userReptile.reptile.feedingFrequency, userReptile.reptile.feedingFrequency)) );

        viewHolder.vertMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(context, viewHolder.vertMenu);
                menu.inflate(R.menu.reptile_item_popup_menu);
                db = AppDatabase.getDatabase(context);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_delete) {
                            db.dao_userReptile().deleteUserReptile((int)userReptile.userReptile.userReptile_id);
                            mItemList.remove(position);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    menu.setForceShowIcon(true);
                }
                menu.show();
            }
        });
    }
    private void addUserPlantItem(AddButtonViewHolder viewHolder, int position) {
        viewHolder.addUserPlantButton.setOnClickListener(view -> {
            Intent i = new Intent(context, AddUserReptileActivity.class);
            context.startActivity(i);
        });

    }
}
