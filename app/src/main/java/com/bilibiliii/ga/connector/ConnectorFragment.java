package com.bilibiliii.ga.connector;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.User;

import java.util.ArrayList;
import java.util.List;

public class ConnectorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView connectorListview;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<User> mUsers=new ArrayList<>();
    private TextView mUsername;


    public ConnectorFragment() {

    }

    public static ConnectorFragment newInstance(String param1, String param2) {
        ConnectorFragment fragment = new ConnectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getData();
    }
    public void getData(){
        User.UserBuilder userBuilder=new User.UserBuilder();
        mUsers.add(userBuilder.setUsername("hawaii").build());
        mUsers.add(userBuilder.setUsername("guoda").build());
        mUsers.add(userBuilder.setUsername("aha").build());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_connector, container, false);
        connectorListview=(ListView) view.findViewById(R.id.connector_listview);
        connectorListview.setAdapter(new ConnectorAdapter());
        return view;
    }

    private class ConnectorAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mUsers.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view=LayoutInflater.from(getContext()).inflate(R.layout.item_connector_listview,viewGroup,false);
            }
            mUsername=(TextView)view.findViewById(R.id.connector_name);
            mUsername.setText(mUsers.get(i).getUsername());
            return view;
        }
    }




}
