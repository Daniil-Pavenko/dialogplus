package com.orhanobut.dialogplus;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ListHolder implements HolderAdapter, AdapterView.OnItemClickListener {

    private int backgroundResource;

    private ListView listView;
    private OnHolderListener listener;
    private View.OnKeyListener keyListener;
    private View headerView;
    private View footerView;

    private boolean enableOverlayBackground = true;
    private boolean enableClickOverlayBackground = true;

    @Override
    public void addHeader(View view) {
        if (view == null) {
            return;
        }
        listView.addHeaderView(view);
        headerView = view;
    }

    @Override
    public void addFooter(View view) {
        if (view == null) {
            return;
        }
        listView.addFooterView(view);
        footerView = view;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void setBackgroundResource(int colorResource) {
        this.backgroundResource = colorResource;
    }

    @Override
    public void setOverlayBackgroundEnable(boolean enableOverlayBackground) {
        this.enableOverlayBackground = enableOverlayBackground;
    }

    @Override
    public void setClickOverlayBackgroundEnable(boolean enableClickOverlayBackground) {
        this.enableClickOverlayBackground = enableClickOverlayBackground;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.dialog_list, parent, false);
        View outMostView = view.findViewById(R.id.dialogplus_outmost_container);

        if (enableOverlayBackground) {
            outMostView.setBackgroundResource(backgroundResource);
        } else {
            outMostView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        outMostView.setClickable(enableClickOverlayBackground);
        outMostView.setFocusable(enableClickOverlayBackground);

        listView = (ListView) view.findViewById(R.id.dialogplus_list);
        listView.setOnItemClickListener(this);
        listView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyListener == null) {
                    throw new NullPointerException("keyListener should not be null");
                }
                return keyListener.onKey(v, keyCode, event);
            }
        });
        return view;
    }

    @Override
    public void setOnItemClickListener(OnHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public void setOnKeyListener(View.OnKeyListener keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public View getInflatedView() {
        return listView;
    }

    @Override
    public View getHeader() {
        return headerView;
    }

    @Override
    public View getFooter() {
        return footerView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener == null) {
            return;
        }
        //ListView counts header as position as well. For consistency we don't
        listener.onItemClick(
                parent.getItemAtPosition(position),
                view,
                headerView != null ? --position : position
        );
    }
}
