package com.QAP2.DogDayCare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@CrossOrigin
public class DogController {
    @Autowired
    private DogService dogService;

    @GetMapping
    public ResponseEntity<List<Dog>> getAllDogs(){
        return ResponseEntity.ok(dogService.getAllDogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        Dog dog = dogService.getDogById(id);
        return dog != null ? ResponseEntity.ok(dog) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Dog>> searchDogs(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "owner", required = false) String owner){
    if (name != null){
        List<Dog> dogsFound = dogService.findByName(name);
        return dogsFound.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(dogsFound);
    } else if (owner != null){
        List<Dog> dogsFound = dogService.findByOwner(owner);
        return dogsFound.isEmpty() ? ResponseEntity.notFound().build() :  ResponseEntity.ok(dogsFound);
    }
    return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dogService.createDog(dog));}

    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(
             @PathVariable Long id,
             @RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "breed", required = false) String breed,
             @RequestParam(value = "age", required = false) Integer age,
             @RequestParam(value = "isVaccinated", required = false) Boolean isVaccinated,
             @RequestParam(value = "owner", required = false) String owner){
        Dog updatedDog = dogService.updateDog(id, name, breed, age, isVaccinated, owner);
        return updatedDog != null ? ResponseEntity.ok(updatedDog) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDogById(@PathVariable Long id){
        Dog dogToDelete = dogService.getDogById(id);
        if (dogToDelete != null) {
            dogService.deleteDogById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
