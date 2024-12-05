package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.Login;
import crafter32sTools.PWDManager.Server.model.User;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LoginRepository extends CrudRepository<Login, UUID> {
    List<Login> getByUserOrderByLast_date_of_changeDesc(User user);
    void deleteById(UUID uuid);

}