package io.pivotal.pal.tracker;



import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private JdbcTemplate jdbcTemplate ;

    public JdbcTimeEntryRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement stmt = con
                        .prepareStatement("INSERT into time_entries(project_id,user_id,date,hours) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setLong(1, timeEntry.getProjectId());
                stmt.setLong(2, timeEntry.getUserId());
                stmt.setDate(3, java.sql.Date.valueOf(timeEntry.getDate()));
                stmt.setInt(4,timeEntry.getHours());
                return stmt;
            }
        }, generatedKeyHolder);

        Number key = generatedKeyHolder.getKey();

        timeEntry.setId(key.longValue());
        return timeEntry;
    }

    private final RowMapper<TimeEntry> mapper = (rs, rowNum) -> new TimeEntry(
            rs.getLong("id"),
            rs.getLong("project_id"),
            rs.getLong("user_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("hours")
    );
    private final ResultSetExtractor<TimeEntry> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;

    @Override
    public TimeEntry find(Long id) {
        return jdbcTemplate.query(
                "SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?",
                new Object[]{id},
                extractor);
    }

    @Override
    public List<TimeEntry> list() {
        return jdbcTemplate.query(
                "SELECT id, project_id, user_id, date, hours FROM time_entries",mapper);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement stmt = con
                        .prepareStatement("update time_entries set project_id=?,user_id=?,date=?,hours=? where id=?");
                stmt.setLong(1, timeEntry.getProjectId());
                stmt.setLong(2, timeEntry.getUserId());
                stmt.setDate(3, java.sql.Date.valueOf(timeEntry.getDate()));
                stmt.setInt(4,timeEntry.getHours());
                stmt.setInt(5, Integer.valueOf(id.toString()));
                return stmt;
            }
        });

        return find(id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM time_entries WHERE id = ?", id);
    }
}
