package crafter32sTools.PWDManager.Server.repository;

import crafter32sTools.PWDManager.Server.model.LoginParam;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LoginParamRepository extends CrudRepository<LoginParam, UUID> {
}