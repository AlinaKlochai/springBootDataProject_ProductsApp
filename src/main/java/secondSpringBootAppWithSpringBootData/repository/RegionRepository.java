package secondSpringBootAppWithSpringBootData.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import secondSpringBootAppWithSpringBootData.entity.Region;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
   Optional<Region> findByRegionName(String name);
   Optional<Region> findById(Long id);
   Optional<Region> findByRegionNewsId(Integer regionNewsId);
}
