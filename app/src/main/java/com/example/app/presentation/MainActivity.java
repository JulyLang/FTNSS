package com.example.app.presentation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


//    private void addFragment(@IdRes int containerViewId,
//                             @NonNull Fragment fragment,
//                             @NonNull String fragmentTag) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(containerViewId, fragment, fragmentTag)
//                .disallowAddToBackStack()
//                .commit();
//    }

    private void replaceFragment(@IdRes int containerViewId,
                                 @NonNull Fragment fragment,
                                 @NonNull String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, fragmentTag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void replaceFragment(View view) {
        replaceFragment(android.R.id.content,
                new MealDetailsFragment(), "MealOneFragment");
    }
}
