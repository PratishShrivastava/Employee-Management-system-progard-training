package com.EmployeeManagementSystem.EmployeeManagementSystem.Controllers;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Assets;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (assets.getAssetName().length()>0&&String.valueOf(assets.getAssetCurrentPrice()).length()>0&&String.valueOf(assets.getAssetPurchasedPrice()).length()>0){
            if(!String.valueOf(assets.getAssetCurrentPrice()).contains("-") && String.valueOf(assets.getAssetPurchasedPrice()).contains("-")) {
                Assets ass = assetService.saveAsset(assets);
                return new ResponseEntity<>("Created Assets", HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("Invalid Data -ve value.",HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<Assets> getAllAssets()
    {
        return assetService.getAllAssets();
    }
    @GetMapping("/{assetId}")
    public ResponseEntity<Assets> getAssetsById(@PathVariable("assetId")int assetId)
    {
        return new ResponseEntity<Assets>(assetService.getAssetsById(assetId), HttpStatus.OK);
    }
    @PutMapping("{assetId}")
    public ResponseEntity<String> updateAssets(@PathVariable("assetId")int assetId,@RequestBody Assets assets)
    {
        assetService.updateAssets(assets, assetId);
        return new ResponseEntity<String>("Updated Assets", HttpStatus.OK);
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
