package com.QAP2.DogDayCare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAllDogs() { return dogRepository.findAll();}

    public Dog getDogById(Long id){
        Optional<Dog> dogOptional = dogRepository.findById(id);
        return dogOptional.orElse(null);}

    public List<Dog> findByName(String name){ return dogRepository.findByName(name);}

    public List<Dog> findByOwner(String owner){return dogRepository.findByOwner(owner);}

    public Dog createDog(Dog newDog) {return dogRepository.save(newDog);}

    public Dog updateDog(Long id, String name, String breed, Integer age, Boolean isVaccinated, String owner){
        Optional<Dog> dogToUpdateOptional = dogRepository.findById(id);

        if (dogToUpdateOptional.isPresent()){
            Dog dogToUpdate = dogToUpdateOptional.get();

            if (name != null){
                dogToUpdate.setName(name);
            }
            if (breed != null){
                dogToUpdate.setBreed(breed);
            }
            if (age != null){
                dogToUpdate.setAge(age);
            }
            if (isVaccinated != null){
                dogToUpdate.setVaccinated(isVaccinated);
            }
            if (owner != null){
                dogToUpdate.setOwner(owner);
            }

            return dogRepository.save(dogToUpdate);
        }
        return null;
    }

    public void deleteDogById(Long id){ dogRepository.deleteById(id);}

}
