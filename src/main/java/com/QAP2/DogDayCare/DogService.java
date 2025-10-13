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

    public Dog findByName(String name){ return dogRepository.findByName(name);}

    public Dog findByOwner(String owner){return dogRepository.findByOwner(owner);}

    public Dog createDog(Dog newDog) {return dogRepository.save(newDog);}

    public Dog updateDog(Long id, Dog updatedDog){
        Optional<Dog> dogToUpdateOptional = dogRepository.findById(id);

        if (dogToUpdateOptional.isPresent()){
            Dog dogToUpdate = dogToUpdateOptional.get();

            dogToUpdate.setName(updatedDog.getName());
            dogToUpdate.setBreed(updatedDog.getBreed());
            dogToUpdate.setAge(updatedDog.getAge());
            dogToUpdate.setVaccinated(updatedDog.isVaccinated());
            dogToUpdate.setOwner(updatedDog.getOwner());

            return dogRepository.save(dogToUpdate);
        }
        return null;
    }

    public void deleteDogById(Long id){ dogRepository.deleteById(id);}

}
