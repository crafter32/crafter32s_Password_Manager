package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @Query("select u from User u where u.userName = ?1 or u.email = ?1")
    @Nullable
    User getByUserNameOrEmail(String userNameOrEmail);

}