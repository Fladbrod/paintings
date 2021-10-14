package painting.paintings.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import painting.paintings.DTO.ArtistDTO;
import painting.paintings.models.Artist;
import painting.paintings.models.Painting;
import painting.paintings.repositories.ArtistRepository;
import painting.paintings.repositories.PaintingRepository;
import java.util.List;

@Api(value = "Paintings controller", description = "REST endpoints for painting")
@RestController
public class Paintings {

    @Autowired
    PaintingRepository paintings;

    @Autowired
    ArtistRepository artists;


    @GetMapping("/paintings")
    public Iterable<Painting> getPainting() {
        return paintings.findAll();
    }

    @GetMapping("/paintings/{id}")
    public Painting getPaintingById(@PathVariable Long id) {
        return paintings.findById(id).get();
    }

    @GetMapping("/paintings/timeline")
    public List<Painting> getPaintingByArtistAndYear(@RequestParam String artist, @RequestParam int year) {
        return paintings.findPaintingsByArtistAndYear(artist, year);
    }

    @GetMapping("/paintings/pricelookup/{price}")
    public List<Painting> getPaintingAboveCertainPrice(@PathVariable double price) {
        System.out.println(price);
        return null;
    }

    @PostMapping("/paintings")
    public Painting addPainting(@RequestBody String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Painting paintingToCreate = mapper.readValue(body, Painting.class);

        Iterable<Long> artistsIds = mapper.readValue(body, ArtistDTO.class).artistsIds;

        List<Artist> foundArtists = (List<Artist>) artists.findAllById(artistsIds);

        paintingToCreate.setArtists(foundArtists);

        return paintings.save(paintingToCreate);
    }

    @PutMapping("/paintings/{id}")
    public Painting updatePaintingById(@PathVariable Long id, @RequestBody Painting newPainting){
        newPainting.setId(id);
        return paintings.save(newPainting);
    }

    @PatchMapping("/paintings/{id}")
    public String patchPaintingById(@PathVariable Long id, @RequestBody Painting paintingToUpdateWith) {
        return paintings.findById(id).map(foundPainting -> {
            if (paintingToUpdateWith.getArtist() != null) foundPainting.setArtist(paintingToUpdateWith.getArtist());
            if (paintingToUpdateWith.getPrice() != 0) foundPainting.setPrice(paintingToUpdateWith.getPrice());
            if (paintingToUpdateWith.getTitle() != null) foundPainting.setTitle(paintingToUpdateWith.getTitle());
            if (paintingToUpdateWith.getGenre() != null) foundPainting.setGenre(paintingToUpdateWith.getGenre());
            if (paintingToUpdateWith.getYear() != 0) foundPainting.setYear(paintingToUpdateWith.getYear());

            paintings.save(foundPainting);
            return "Painting updated";
        }).orElse("Painting not found");
    }

    @DeleteMapping("/paintings/{id}")
    public void deletePaintingById(@PathVariable Long id){
        paintings.deleteById(id);
    }
}
