//package painting.paintings;
//
//import painting.paintings.models.Artist;
//import painting.paintings.repositories.ArtistRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ArtistRepositoryIntegrationTest {
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Autowired
//    private ArtistRepository artists;
//
//    @Test
//    public void whenFindByNameThenReturnArtist(){
//        //given
//        Artist artist = new Artist();
//        artist.setName("ø");
//        testEntityManager.persist(artist);
//        testEntityManager.flush();
//
//        //when
//        Artist artistFound = artists.findByName("ø");
//
//        //then
//        assertEquals(artistFound.getName(), artist.getName());
//    }
//}
