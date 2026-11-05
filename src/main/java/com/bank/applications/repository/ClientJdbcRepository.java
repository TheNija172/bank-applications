package com.bank.applications.repository;

import com.bank.applications.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Client> findAll() {

        String sql = """ 
                SELECT id, first_name, last_name, email, phone, created_at, updated_at
                FROM clients
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Client client = new Client();

            client.setId(rs.getLong("id"));
            client.setFirstName(rs.getString("first_name"));
            client.setLastName(rs.getString("last_name"));
            client.setEmail(rs.getString("email"));
            client.setPhone(rs.getString("phone"));
            client.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

            Timestamp updatedAt = rs.getTimestamp("updated_at");

            if (updatedAt != null) {
                client.setUpdatedAt(updatedAt.toLocalDateTime());
            }

            return client;
        });
    }
}
