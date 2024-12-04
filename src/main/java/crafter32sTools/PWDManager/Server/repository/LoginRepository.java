package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.Login;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LoginRepository extends CrudRepository<Login, UUID> {
}