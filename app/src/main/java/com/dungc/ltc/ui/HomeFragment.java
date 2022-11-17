package com.dungc.ltc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.dungc.ltc.R;
import com.dungc.ltc.databinding.FragmentHomeBinding;
import com.dungc.ltc.fcm.MyFirebaseMessagingService;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getActivity()!= null) {
//            handleIntent(getActivity().getIntent());
//        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.ivNotify.setOnClickListener(view1 -> {
//            redirectNotification();
//        });
    }

    private void handleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Log.d("HomeFragment", "Redirect notify -->");
            String title = bundle.getString(MyFirebaseMessagingService.NOTIFICATION_KEY);
            redirectNotification();
        }
    }

    private void redirectNotification() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(
                R.id.action_home_to_notify,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(R.anim.anim_enter_from_right)
                        .setExitAnim(R.anim.anim_exit_to_left)
                        .setPopEnterAnim(R.anim.anim_enter_from_left)
                        .setPopExitAnim(R.anim.anim_exit_to_right)
                        .build()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
