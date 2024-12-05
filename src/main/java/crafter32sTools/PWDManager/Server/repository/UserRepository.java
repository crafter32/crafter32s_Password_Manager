package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    @Query("select u from User u where u.userName = ?1 or u.email = ?1")
    @Nullable
    User getByUserNameOrEmail(String userNameOrEmail);

}