package painting.paintings.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import painting.paintings.models.Painting;
import painting.paintings.repositories.PaintingRepository;
import java.util.List;

@Api(value = "Paintings controller", description = "REST endpoints for painting")
@RestController
public class Paintings {

    @Autowired
    PaintingRepository paintings;

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
    public Painting addPainting(@RequestBody Painting newPainting) {
        return paintings.save(newPainting);
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
            if (paintingToUpdateWith.getPrices() != 0) foundPainting.setPrices(paintingToUpdateWith.getPrices());
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
