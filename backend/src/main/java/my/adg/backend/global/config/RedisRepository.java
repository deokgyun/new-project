package my.adg.backend.global.config;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RedisToken, String> {

}
