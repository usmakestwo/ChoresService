
package com.usmakestwo.chores.handler;

/**
 * Import networknt libraries
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.security.JwtHelper;
import com.networknt.config.Config;
import com.networknt.status.Status;
import com.networknt.service.SingletonServiceFactory;


import com.usmakestwo.chores.model.Chore;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * Class implements the retrieval of chore information, as queried by chore ID.
 */
public class ChoresChoreIdGetHandler implements HttpHandler {
    // Set up logger
    static final Logger logger = LoggerFactory.getLogger(ChoresChoreIdGetHandler.class);
    static Map<String, Object> securityConfig = (Map)Config.getInstance().getJsonMapConfig(JwtHelper.SECURITY_CONFIG);
    static boolean securityEnabled = (Boolean)securityConfig.get(JwtHelper.ENABLE_VERIFY_JWT);

    // Access a configured DataSource;
    private static final DataSource ds = (DataSource) SingletonServiceFactory.getBean(DataSource.class);

    // Get a Jackson JSON Object Mapper, usuable for object serialization
    private static final ObjectMapper mapper = Config.getInstance().getMapper();

    /**
     * Handle Request
     * @param exchange
     * @throws Exception
     */
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }

        Status status = null;
        int statusCode = 200;
        String resp = null;

        // get chore id here.
        Integer choreId = Integer.valueOf(exchange.getQueryParameters().get("id").getFirst());
        Chore chore = null;

        // Get data from SQL
        try (final Connection connection = ds.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM chore WHERE id = ?",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

                statement.setInt(1, choreId);

                try(ResultSet resultSet = statement.executeQuery()) {

                    // extract chore data
                    if (resultSet.next()) {
                        // chore data successfully retrieved

                        chore = new Chore();
                        chore.setId(resultSet.getInt("ID"));
                        chore.setName(Helper.isNull(resultSet.getString("NAME")));
                        chore.setRecurrent(Helper.isNull(resultSet.getString("RECURRENT")));
                        chore.setCompleted(resultSet.getBoolean("COMPLETED"));

                        // serialize the response
                        Map<String, Chore> map = new HashMap<String, Chore>();
                        map.put("chores", chore);

                        resp = mapper.writeValueAsString(map);
                    } else {
                        // chore data not found
                        status = new Status("ERR12013", choreId);
                        statusCode = status.getStatusCode();

                        // serialize the error response
                        resp = mapper.writeValueAsString(status);
                    }
                }
            }
        } catch (Exception e) {
            // log the exception
            logger.error("Exception encountered in the chores API", e);

            // This is a runtime exception
            status = new Status("ERR10010");
            statusCode = status.getStatusCode();

            // serialize the error response
            resp = mapper.writeValueAsString(status);
        }

        // set the content type in response
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");


        // serialize the response object and set in the response
        exchange.setStatusCode(statusCode);
        exchange.getResponseSender().send(resp);
    }
}

