package com.indexlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cropimagetest.RoundImageView;
import com.git.easylib.R;

import java.util.List;

public class SortAdapter extends BaseAdapter{

    private List<User> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<User> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        final User user = list.get(position);
        view = LayoutInflater.from(mContext).inflate(R.layout.maillist_item, null);
        TextView name = (TextView) view.findViewById(R.id.txtName);
        TextView catalog = (TextView) view.findViewById(R.id.catalog);

        TextView txtPosition = (TextView) view.findViewById(R.id.txtPosition);
        TextView txtNumber = (TextView) view.findViewById(R.id.txtNumber);

        RoundImageView main_roundImage = (RoundImageView) view.findViewById(R.id.roundImage);
        ImageView imgIsPTA = (ImageView) view.findViewById(R.id.imgIsPTA);

        //根据position获取首字母作为目录catalog
        String catalogValue = list.get(position).getFirstLetter();

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(catalogValue)){
            catalog.setVisibility(View.VISIBLE);
            catalog.setText(user.getFirstLetter().toUpperCase());
        }else{
            catalog.setVisibility(View.GONE);
        }

        name.setText(user.getName());
        if (user.getPosition() != null && !user.getPosition().equals("")){
            txtPosition.setVisibility(View.VISIBLE);
            txtPosition.setText(user.getPosition());
            imgIsPTA.setVisibility(View.VISIBLE);
        }
        txtNumber.setText(user.getNumber());
//        if (view == null) {
//            viewHolder = new ViewHolder();
//            view = LayoutInflater.from(mContext).inflate(R.layout.maillist_item, null);
//            viewHolder.name = (TextView) view.findViewById(R.id.txtName);
//            viewHolder.catalog = (TextView) view.findViewById(R.id.catalog);
//
//            viewHolder.txtPosition = (TextView) view.findViewById(R.id.txtPosition);
//            viewHolder.txtNumber = (TextView) view.findViewById(R.id.txtNumber);
//
//            viewHolder.main_roundImage = (RoundImageView) view.findViewById(R.id.main_roundImage);
//            viewHolder.imgIsPTA = (ImageView) view.findViewById(R.id.imgIsPTA);
//            view.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) view.getTag();
//        }

        //根据position获取首字母作为目录catalog
//        String catalog = list.get(position).getFirstLetter();
//
//        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
//        if(position == getPositionForSection(catalog)){
//            viewHolder.catalog.setVisibility(View.VISIBLE);
//            viewHolder.catalog.setText(user.getFirstLetter().toUpperCase());
//        }else{
//            viewHolder.catalog.setVisibility(View.GONE);
//        }
//
//        viewHolder.name.setText(user.getName());
//        if (user.getPosition() != null && !user.getPosition().equals("")){
//            viewHolder.txtPosition.setVisibility(View.VISIBLE);
//            viewHolder.txtPosition.setText(user.getPosition());
//            viewHolder.imgIsPTA.setVisibility(View.VISIBLE);
//        }
//        viewHolder.txtNumber.setText(user.getNumber());

        return view;

    }

    final static class ViewHolder {
        TextView catalog;
        TextView name;
        TextView txtPosition;
        TextView txtNumber;
        RoundImageView main_roundImage;
        ImageView imgIsPTA;
    }

    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }

}