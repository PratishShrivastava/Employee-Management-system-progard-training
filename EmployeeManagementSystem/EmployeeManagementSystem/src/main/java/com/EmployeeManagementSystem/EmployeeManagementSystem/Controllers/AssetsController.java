package com.EmployeeManagementSystem.EmployeeManagementSystem.Controllers;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Assets;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/assets")
public class AssetsController {
    @Autowired
    private AssetService assetService;

    public AssetsController(AssetService assetService){
        this.assetService = assetService;
    }
//    @PostMapping
//    public ResponseEntity<Assets> saveAsset(@RequestBody Assets assets)
//    {
//        return new ResponseEntity<Assets>(assetService.saveAsset(assets), HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<String> saveAssets(@RequestBody Assets assets) {
        if (assets.getAssetName().length()>0 && String.valueOf(assets.getAssetCurrentPrice()).length()-1>0&&String.valueOf(assets.getAssetPurchasedPrice()).length()-1>0){
            if(String.valueOf(assets.getAssetCurrentPrice()).contains("-") || String.valueOf(assets.getAssetPurchasedPrice()).contains("-")) {
                return new ResponseEntity<>("Invalid Data -ve value.",HttpStatus.BAD_REQUEST);
            }else {
                Assets ass = assetService.saveAsset(assets);
                return new ResponseEntity<>("Created Assets", HttpStatus.CREATED);
            }
        }
        else {
            return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<List<Assets>> getAllAssets()
    {
        List<Assets> assets = assetService.getAllAssets();
        if(assets.size()>0){
            return new ResponseEntity<>(assets,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{assetId}")
    public ResponseEntity<Assets> getAssetsById(@PathVariable("assetId")int assetId)
    {
        try {
            return new ResponseEntity<Assets>(assetService.getAssetsById(assetId), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Assets>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{assetId}")
    public ResponseEntity<String> updateAssets(@PathVariable("assetId")int assetId,@RequestBody Assets assets)
    {
        try {
            if(!String.valueOf(assets.getAssetCurrentPrice()).contains("-") && String.valueOf(assets.getAssetPurchasedPrice()).contains("-")) {
                assetService.updateAssets(assets, assetId);
                return new ResponseEntity<String>("Updated Assets", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Invalid Data -ve value.",HttpStatus.BAD_REQUEST);
            }
        }catch (NoSuchElementException e){
            return new ResponseEntity<String>("Assets details not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{assetId}")
    public ResponseEntity<String> deleteAssets(@PathVariable("assetId")int assetId)
    {
        if(assetService.deleteAssets(assetId))
            return new ResponseEntity<String>("Assets deleted successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }
}
