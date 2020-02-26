package eu.shagov.router;

import eu.shagov.handler.ItemsHandler;
import eu.shagov.constants.ItemConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ItemsRouter {

    @Bean
    public RouterFunction<ServerResponse> itemsRoute(ItemsHandler itemsHandler) {

        return RouterFunctions
                .route(GET(ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1).and(accept(MediaType.APPLICATION_JSON))
                        , itemsHandler::getAllItems)
                .andRoute(GET(ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1 + "/{id}").and(accept(MediaType.APPLICATION_JSON))
                        , itemsHandler::getOneItem)
                .andRoute(POST(ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1).and(accept(MediaType.APPLICATION_JSON))
                        , itemsHandler::createItem)
                .andRoute(DELETE(ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1 + "/{id}").and(accept(MediaType.APPLICATION_JSON))
                        , itemsHandler::deleteItem);
    }
}
