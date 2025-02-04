package my.adg.backend.global.dto.request;

import java.util.Optional;

import org.springframework.boot.context.properties.bind.DefaultValue;

public record PageDataRequest(
	Optional<Integer> page
) {
}
