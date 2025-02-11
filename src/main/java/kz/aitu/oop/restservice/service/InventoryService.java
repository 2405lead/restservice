package kz.aitu.oop.restservice.service;

import kz.aitu.oop.restservice.entity.Inventory;
import kz.aitu.oop.restservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
        return inventoryRepository.findById(id).map(inventory -> {
            inventory.setProduct(inventoryDetails.getProduct());
            inventory.setQuantity(inventoryDetails.getQuantity());
            inventory.setLocation(inventoryDetails.getLocation());
            return inventoryRepository.save(inventory);
        }).orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
