package com.geo.sampleapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geo.sampleapplication.R;
import com.geo.sampleapplication.databinding.RowItemCountryBinding;
import com.geo.sampleapplication.model.ApiResponseItem;

import java.util.List;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.CountryRecyclerViewHolder> {

    private List<ApiResponseItem> mApiResponseItems;

    public CountryRecyclerAdapter() {
    }

    public void setProducts(List<ApiResponseItem> apiResponseItems) {
        mApiResponseItems = apiResponseItems;
    }

    @NonNull
    @Override
    public CountryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowItemCountryBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.row_item_country,
                        parent,
                        false);
        return new CountryRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecyclerViewHolder holder, int position) {
        holder.bindCountry(mApiResponseItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mApiResponseItems.size();
    }

    public static class CountryRecyclerViewHolder extends RecyclerView.ViewHolder {
        private final RowItemCountryBinding mBinding;

        public CountryRecyclerViewHolder(@NonNull RowItemCountryBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindCountry(ApiResponseItem apiResponseItem) {
            mBinding.countryName.setText(apiResponseItem.getName());
        }
    }
}
