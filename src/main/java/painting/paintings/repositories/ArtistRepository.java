package painting.paintings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import painting.paintings.models.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    
}
