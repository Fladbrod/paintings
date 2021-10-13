package painting.paintings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import painting.paintings.models.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}
