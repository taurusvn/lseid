package com.dungc.ltc.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dungc.ltc.R;
import com.dungc.ltc.adapter.NotifyAdapter;
import com.dungc.ltc.databinding.FragmentHomeBinding;
import com.dungc.ltc.databinding.FragmentNotifyBinding;
import com.dungc.ltc.model.Notify;
import com.dungc.ltc.network.ApiService;
import com.dungc.ltc.network.ApiUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifyFragment extends Fragment {
    private FragmentNotifyBinding binding;
    private ApiService mService;
    private NotifyAdapter notifyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotifyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mService = ApiUtil.getService();

        notifyAdapter = new NotifyAdapter();
        binding.rvNotify.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNotify.setAdapter(notifyAdapter);

        binding.ivBack.setOnClickListener(view1 -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        loadData();
    }

    public void loadData() {
        mService.getNotifications().enqueue(new Callback<List<Notify>>() {
            @Override
            public void onResponse(@NonNull Call<List<Notify>> call, @NonNull Response<List<Notify>> response) {
                if (response.isSuccessful()) {
                    notifyAdapter.initDatas(response.body());
                } else {
                    // handle request errors depending on status code
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Notify>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error loading from API ---> " + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
