package com.nonesofar.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<List<Asset>> getSpecificAssets(@PathVariable String name) {
		try {
			System.out.println("clean " + name);
			List<Asset> assetList = assetRepo.findAll();
			List<Asset> searchMatches = new ArrayList<Asset>();

			for (Asset asset : assetList) {
				System.out.println("Searching " + asset.getName());
				if (asset.getName().equals(name)) {
					searchMatches.add(asset);
				}
			}

			if (assetList.isEmpty() || assetList.size() == 0) {
				return new ResponseEntity<List<Asset>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Asset>>(searchMatches, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/assets/{sNo}/{name}/{Assignment_Status}")
	public ResponseEntity<Asset> updateItem(@PathVariable long sNo, @PathVariable String name,
			@PathVariable String Assignment_Status) {
		try {
			Asset anAsset = assetRepo.findById(sNo).get();
			anAsset.setName(name);
			anAsset.setAssignmentstatus(Assignment_Status);

			return new ResponseEntity<>(assetRepo.save(anAsset), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/assets/{sNo}/{managedBy}")
	public ResponseEntity<Asset> assignManager(@PathVariable long sNo, @PathVariable String managedBy) {
		try {
			Asset anAsset = assetRepo.findById(sNo).get();
			anAsset.setManagedBy(managedBy);
			anAsset.setAssignmentstatus("Assigned");
			assetRepo.save(anAsset);
			return new ResponseEntity<>(assetRepo.save(anAsset), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("/assets/{sNo}/{managedBy}")
	public ResponseEntity<Asset> assignManagder(@PathVariable long sNo, @PathVariable String managedBy) {
		try {
			Asset anAsset = assetRepo.findById(sNo).get();
			anAsset.setManagedBy(managedBy);
			anAsset.setAssignmentstatus("Assigned");
			assetRepo.save(anAsset);
			return new ResponseEntity<>(assetRepo.save(anAsset), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/assets/{sNo}")
	public ResponseEntity<Asset> deleteEntry(@PathVariable long sNo) {
		try {
			if (!assetRepo.getById(sNo).getAssignmentstatus().equalsIgnoreCase("assigned")) {
				Asset anAsset = assetRepo.findById(sNo).get();
				assetRepo.deleteById(sNo);
				return new ResponseEntity<>(anAsset, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
