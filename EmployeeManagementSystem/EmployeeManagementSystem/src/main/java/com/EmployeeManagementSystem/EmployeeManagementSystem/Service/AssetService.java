package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Assets;

import java.util.List;

public interface AssetService {
    Assets saveAsset(Assets assets);
    List<Assets> getAllAssets();
    Assets getAssetsById(int assetId);
    Assets updateAssets(Assets assets, int assetId);
    boolean deleteAssets(int assetId);
}