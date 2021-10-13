package painting.paintings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import painting.paintings.models.Painting;

import java.util.List;

public interface PaintingRepository extends JpaRepository<Painting, Long> {

    List<Painting> findPaintingsByArtistAndYear(String artistName, int year);

    @Query(value = "SELECT * FROM artists WHERE price > ?", nativeQuery = true)
    List<Painting> getPaintingsAboveACertainPrice();


}
