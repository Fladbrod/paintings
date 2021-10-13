package painting.paintings.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import painting.paintings.models.Artist;
import painting.paintings.repositories.ArtistRepository;

@RestController
public class Artists {

    @Autowired
    ArtistRepository repo;

    //Getting all Artists
    @GetMapping("/artists")
    public Iterable<Artist> getRepo() {
        return repo.findAll();
    }
    //Getting a artist by id
    @GetMapping("/artists/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return repo.findById(id).get();
    }
    //Add's an artist using post
    @PostMapping("/artists")
    public Artist addArtist(@RequestBody Artist newArtist) {
        return repo.save(newArtist);
    }

    @PostMapping("/artists/gallery/{galleryId}/{artistId}")
    public Artist addPaintingToArtist(@PathVariable Long galleryId, @PathVariable Long artistId) {
        System.out.println(artistId);
        System.out.println(galleryId);
        return null;
    }

    //Updates a artists by id
    @PutMapping("/artists/{id}")
    public Artist updateArtistById(@PathVariable Long id, @RequestBody Artist newArtist){
        newArtist.setId(id);
        return repo.save(newArtist);
    }


    //Delete an artists by id.
    @DeleteMapping("/artists/{id}")
    public void deleteArtistById(@PathVariable long id){
        repo.deleteById(id);
    }
}