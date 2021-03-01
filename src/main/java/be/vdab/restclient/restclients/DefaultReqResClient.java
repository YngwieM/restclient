package be.vdab.restclient.restclients;

import be.vdab.restclient.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
class DefaultReqResClient implements ReqResClient {
    private final WebClient client;
    private final String eenUserURI;
    DefaultReqResClient(WebClient.Builder builder,
                        @Value("${reqres.eenUser}") String eenUserURI) {
        client = builder.build();
        this.eenUserURI = eenUserURI;
    }
    @Override
    public Optional<User> findById(long id) {
        try {
            return Optional.of(client.get()
                    .uri(eenUserURI, uriBuilder->uriBuilder.build(id))
 .retrieve().bodyToMono(User.class).block());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }
}