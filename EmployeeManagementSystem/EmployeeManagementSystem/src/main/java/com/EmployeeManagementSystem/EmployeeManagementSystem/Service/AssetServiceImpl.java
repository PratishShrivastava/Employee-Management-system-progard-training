package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Assets;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Repository.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    public AssetRepo assetRepo;

    public AssetServiceImpl(AssetRepo assetRepo){
        this.assetRepo = assetRepo;
    }
    @Override
    public Assets saveAsset(Assets assets)
    {
        return assetRepo.save(assets);
    }
    @Override
    public List<Assets> getAllAssets()
    {
        return assetRepo.findAll();
    }
    @Override
    public Assets getAssetsById(int assetId)
    {
        return assetRepo.findById(assetId).orElseThrow();
    }
    @Override
    public Assets updateAssets(Assets assets,int assetId)
    {
        Assets user = assetRepo.findById(assetId).orElseThrow();
        user.setAssetName(assets.getAssetName());
        user.setAssetCurrentPrice(assets.getAssetCurrentPrice());
        user.setAssetPurchasedPrice(assets.getAssetPurchasedPrice());
        assetRepo.save(user);
        return user;
    }
    @Override
    public boolean deleteAssets(int assetId)
    {
        try{
            assetRepo.findById(assetId).orElseThrow();
            assetRepo.deleteById(assetId);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }

    }
}