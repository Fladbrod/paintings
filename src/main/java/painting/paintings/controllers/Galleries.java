package painting.paintings.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import painting.paintings.models.Artist;
import painting.paintings.models.Gallery;
import painting.paintings.repositories.GalleryRepository;

import java.util.ArrayList;


@RestController
public class Galleries {

    @Autowired
    GalleryRepository galleries;

    @GetMapping("/galleries")
    public Iterable<Gallery> getGalleries() {
        return galleries.findAll();
    }

    @GetMapping("/galleries/{id}")
    public Gallery getGallery(@PathVariable Long id) {
        return galleries.findById(id).get();
    }

    @PostMapping("/galleries")
    public Gallery addGallery(@RequestBody Gallery newGallery) {
        // don't allow the client to overwrite the id
        newGallery.setId(null);
        return galleries.save(newGallery);
    }

    @PutMapping("/galleries/{id}")
    public String updateGalleryWithId(@PathVariable Long id, @RequestBody Gallery galleryToUpdateWith) {
        if (galleries.existsById(id)) {
            galleryToUpdateWith.setId(id);
            galleries.save(galleryToUpdateWith);
            return "Artist was created";
        } else {
            return "Artist not found";
        }
    }


    @PatchMapping("/galleries/{id}")
    public String patchGalleryById(@PathVariable Long id, @RequestBody Gallery galleryToUpdateWith) {
        return galleries.findById(id).map(foundArtist -> {
            if (galleryToUpdateWith.getName() != null) foundArtist.setName(galleryToUpdateWith.getName());
            if (galleryToUpdateWith.getOwner() != null) foundArtist.setOwner(galleryToUpdateWith.getOwner());
            if (galleryToUpdateWith.getLocation() != null) foundArtist.setLocation(galleryToUpdateWith.getLocation());
            if (galleryToUpdateWith.getSquareFeet() != 0) foundArtist.setSquareFeet(galleryToUpdateWith.getSquareFeet());

            galleries.save(foundArtist);
            return "Artist updated";
        }).orElse("Artist not found");
    }

    @DeleteMapping("/galleries/{id}")
    public void deleteGalleryById(@PathVariable Long id) {
        galleries.deleteById(id);
    }
}

