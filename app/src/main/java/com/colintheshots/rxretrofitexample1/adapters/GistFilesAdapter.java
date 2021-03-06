package com.colintheshots.rxretrofitexample1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.colintheshots.rxretrofitexample1.R;
import com.colintheshots.rxretrofitexample1.models.GistDetail;
import com.colintheshots.rxretrofitexample1.models.GistFile;
import com.google.common.collect.Iterables;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by colin.lee on 10/11/14.
 */
public class GistFilesAdapter extends BaseAdapter {

    private Context mContext;
    private Map<String, GistFile> gistFileMap;

    public GistFilesAdapter(Context mContext, GistDetail gistDetail) {
        this.mContext = mContext;
        this.gistFileMap = gistDetail.getFiles();
    }

    @Override
    public int getCount() {
        return gistFileMap.size();
    }

    @Override
    public Object getItem(int i) {
        String key = Iterables.get(gistFileMap.keySet(), i);
        return gistFileMap.get(key);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GistFileHolder holder;
        if (view != null) {
            holder = (GistFileHolder) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.row_gist, viewGroup, false);
            holder = new GistFileHolder(view);
            view.setTag(holder);
        }

        GistFile gistFile = (GistFile) getItem(i);

        holder.description.setText(gistFile.getContent());
        holder.id.setText(gistFile.getContent());

        return view;
    }

    static class GistFileHolder {
        @InjectView(R.id.gistTextView)
        TextView description;
        @InjectView(R.id.hiddenIdTextView) TextView id;

        public GistFileHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
