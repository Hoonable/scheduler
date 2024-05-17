package com.sparta.project1.repository;

import com.sparta.project1.dto.ScheduleRequestDto;
import com.sparta.project1.dto.ScheduleResponseDto;
import com.sparta.project1.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public Schedule save(Schedule schedule) {

        //DB저장
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO schedule (title, content, manager, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContent());
                    preparedStatement.setString(3, schedule.getManager());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setString(5, schedule.getDate());
                    return preparedStatement;
                },
                keyHolder);
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                ;
                String manager = rs.getString("manager");
                String password = rs.getString("password");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, title, content, manager, password, date);

            }


        });
    }



    public void update(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule SET title = ?, content = ?, manager = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContent(), requestDto.getManager(), id);

    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContent(resultSet.getString("content"));
                schedule.setManager(resultSet.getString("manager"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setDate(resultSet.getString("date"));


                return schedule;
            } else {
                return null;
            }
        }, id);
    }

}
