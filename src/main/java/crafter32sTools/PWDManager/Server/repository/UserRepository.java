package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}