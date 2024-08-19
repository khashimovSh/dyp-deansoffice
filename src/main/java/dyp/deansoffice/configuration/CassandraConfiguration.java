package dyp.deansoffice.configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.cognitor.cassandra.migration.spring.CassandraMigrationAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
class CassandraConfiguration {

    @Value("${cassandra.username}")
    private String username;

    @Value("${cassandra.password}")
    private String password;

    @Value("${cassandra.local-datacenter}")
    private String localDatacenter;

    @Value("${cassandra.keyspace-name}")
    private String keyspace;

    @Lazy
    @Bean
    @Qualifier(CassandraMigrationAutoConfiguration.CQL_SESSION_BEAN_NAME)
    public CqlSession cassandraMigrationCqlSession(CqlSessionBuilder cqlSessionBuilder) {
        return cqlSessionBuilder
                .withAuthCredentials(username, password)
                .withLocalDatacenter(localDatacenter)
                .withKeyspace(keyspace)
                .build();
    }

    @Lazy
    @Bean
    @Primary
    public CqlSession cassandraSession(CqlSessionBuilder cqlSessionBuilder) {
        return cqlSessionBuilder
                .withAuthCredentials(username, password)
                .withLocalDatacenter(localDatacenter)
                .withKeyspace(keyspace)
                .build();
    }
}