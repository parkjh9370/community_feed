package main.common.idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency {

    private final String key;
    private final Response<?> response;
}
