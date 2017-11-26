
package com.usmakestwo.chores;

import com.networknt.config.Config;
import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;
import com.networknt.info.ServerInfoGetHandler;
import com.networknt.health.HealthGetHandler;
import com.usmakestwo.chores.handler.*;

public class PathHandlerProvider implements HandlerProvider {
    @Override
    public HttpHandler getHandler() {
        return Handlers.routing()
        
            .add(Methods.GET, "/chore/v1/health", new HealthGetHandler())
        
            .add(Methods.POST, "/chore/v1/chores", new ChoresPostHandler())
        
            .add(Methods.GET, "/chore/v1/chores", new ChoresGetHandler())
        
            .add(Methods.GET, "/chore/v1/server/info", new ServerInfoGetHandler())
        
            .add(Methods.GET, "/chore/v1/chores/{choreId}", new ChoresChoreIdGetHandler())
        
            .add(Methods.DELETE, "/chore/v1/chores/{choreId}", new ChoresChoreIdDeleteHandler())
        
        ;
    }
}
