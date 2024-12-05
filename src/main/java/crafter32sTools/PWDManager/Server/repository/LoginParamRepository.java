package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.LoginParam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginParamRepository extends CrudRepository<LoginParam, UUID> {
}