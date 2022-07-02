package com.nonesofar.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nonesofar.springboot.entity.Asset;
import com.nonesofar.springboot.repository.AssetsRepo;

@RestController
public class AssetController {

	@Autowired
	AssetsRepo assetRepo;

	@PostMapping("/assets")
	public ResponseEntity<Asset> save(@RequestBody Asset asset) {
		try {
			return new ResponseEntity<>(assetRepo.save(asset), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/assets")
	public ResponseEntity<List<Asset>> getAllAssets() {
		try {
			List<Asset> assetList = assetRepo.findAll();
			if (assetList.isEmpty() || assetList.size() == 0) {
				return new ResponseEntity<List<Asset>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Asset>>(assetList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/assets/{name}")
	public ResponseEntity<List<Asset>> getSpecificlAssets(@PathVariable String name) {
		try {
			System.out.println("clean " + name);
			List<Asset> assetList = assetRepo.findAll();
			List<Asset> searchMatches = new ArrayList<Asset>();

			for (Asset asset : assetList) {
				System.out.println("Searching " + asset.getName());
				if (asset.getName().equals(name)) {
					System.out.println("Match Found");
					searchMatches.add(asset);
				}
			}

			if (assetList.isEmpty() || assetList.size() == 0) {
				System.out.println("im null bruh");
				return new ResponseEntity<List<Asset>>(HttpStatus.NO_CONTENT);
			}
			System.out.println("i aint null bruh");
			return new ResponseEntity<List<Asset>>(searchMatches, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/assets/{id}/{name}/{Assignment_Status}")
	public void updateItemName(@PathVariable long id, @PathVariable String name,
			@PathVariable String Assignment_Status) {

		Asset anAsset = assetRepo.findById(id).get();
		anAsset.setName(name);
		anAsset.setAssignmentstatus(Assignment_Status);
		assetRepo.save(anAsset);

		System.out.println(id + "  " + name);
	}

}
