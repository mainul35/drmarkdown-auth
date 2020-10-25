package com.drmarkdown.auth.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.index.IndexDefinition;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String databaseHost;
    @Value("${spring.data.mongodb.database}")
    private String database;
    /**
     * Return the name of the database to connect to.
     *
     * @return must not be {@literal null}.
     */
    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    /**
     * Return the {@link MongoClient} instance to connect to. Annotate with {@link Bean} in case you want to expose a
     * {@link MongoClient} instance to the {@link ApplicationContext}. <br />
     * Override {@link #mongoClientSettings()} to configure connection details.
     *
     * @return never {@literal null}.
     * @see #mongoClientSettings()
     * @see #configureClientSettings(Builder)
     */
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(this.databaseHost);
    }

    /**
     * Configure whether to automatically create indices for domain types by deriving the
     * {@link IndexDefinition} from the entity or not.
     *
     * @return {@literal false} by default. <br />
     * <strong>INFO</strong>: As of 3.x the default is set to {@literal false}; In 2.x it was {@literal true}.
     * @since 2.2
     */
    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
