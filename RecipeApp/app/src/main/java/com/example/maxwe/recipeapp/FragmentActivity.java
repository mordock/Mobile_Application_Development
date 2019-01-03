package com.example.maxwe.recipeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentActivity extends android.support.v4.app.Fragment {
    private Unbinder unbinder;

    private static final String ARG_RECIPE_ID = "arg_recipe_id";
    private Recipe recipe;

    @BindView(R.id.imageView)
    ImageView imageView;

    public static FragmentActivity newInstance(Recipe recipe) {
        FragmentActivity fragment = new FragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_RECIPE_ID, recipe);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe = getArguments().getParcelable(ARG_RECIPE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View recipeView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, recipeView);
        unbinder = ButterKnife.bind(this, recipeView);
        Glide.with(this).load(recipe.getImageUrl()).into(imageView);

        return recipeView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
