package com.example.vivek.recycleviewdatabasedeleteandsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class UserInputViewAdapter extends RecyclerView.Adapter<UserInputViewAdapter.UserViewHolder>{

    public ArrayList<ListDataModel> usersList=new ArrayList<>();
    public ArrayList<ListDataModel> selected_usersList=new ArrayList<>();
    ArrayList<ListDataModel> mModel;
    Context mContext;

    public UserInputViewAdapter(ArrayList<ListDataModel> usersList, ArrayList<ListDataModel> selected_usersList, Context mContext) {
        this.usersList = usersList;
        this.selected_usersList = selected_usersList;
        this.mContext = mContext;
    }

    @Override
    public UserInputViewAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserInputViewAdapter.UserViewHolder holder, int position) {


        holder.textViewFirstName.setText(usersList.get(position).getFirstName());
        holder.textViewLastName.setText(usersList.get(position).getPhoneNumber());


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }




    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewFirstName;
        public TextView textViewLastName;

        public UserViewHolder(View itemView) {
            super(itemView);
            textViewFirstName = (TextView) itemView.findViewById(R.id.textView_display_first_name);
            textViewLastName = (TextView) itemView.findViewById(R.id.textView_display_last_name);
        }

    }

    public void setFilter(List<ListDataModel> usersModel) {
        mModel = new ArrayList<>();
        mModel.addAll(usersModel);
        notifyDataSetChanged();
    }

}

