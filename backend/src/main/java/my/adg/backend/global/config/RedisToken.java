package my.adg.backend.global.config;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "token", timeToLive = 60)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

}
