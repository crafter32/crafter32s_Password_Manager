package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.Login;
import crafter32sTools.PWDManager.Server.model.User;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoginRepository extends CrudRepository<Login, UUID> {
    @Query("select l from Login l inner join l.loginParams loginParams where loginParams.id = ?1")
    Login findByLoginParams_Id(UUID id);

    List<Login> getByUserOrderByLastDateOfChangeDesc(User user);
    void deleteById(UUID uuid);

}