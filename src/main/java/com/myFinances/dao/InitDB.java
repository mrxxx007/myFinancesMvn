package com.myFinances.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by user on 5/22/2014.
 */
public class InitDB {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void initDB() {
        jdbcTemplate.update("DROP TABLE IF EXISTS USER;");
        jdbcTemplate.execute("create table user (id int not null auto_increment," +
                "    login varchar(25)," +
                "    password VARCHAR(255)," +
                "    primary key(id));");

        jdbcTemplate.update("DROP TABLE IF EXISTS CATEGORY;");
        jdbcTemplate.execute("create table category ( " +
                "    id int not null auto_increment, " +
                "    name varchar(50) not null," +
                "    user_id int, primary key(id)," +
                "    foreign key (user_id)" +
                "    references user(id));");

        jdbcTemplate.update("DROP TABLE IF EXISTS importancy;");
        jdbcTemplate.execute("create table importancy (" +
                "    id int, " +
                "    name varchar(50) not null, primary key(id));");

        jdbcTemplate.update("DROP TABLE IF EXISTS note;");
        jdbcTemplate.execute("create table note (" +
                "    id int not null auto_increment," +
                "    amount double not null," +
                "    category_id varchar(30) not null," +
                "    importancy_id varchar(30)," +
                "    notedate date not null," +
                "    info varchar(100)," +
                "    user_id int, primary key(id)," +
                "    foreign key (user_id)" +
                "    references user(id)," +
                "    foreign key (category_id)" +
                "    references category(id)," +
                "    foreign key (importancy_id)" +
                "    references importancy(id));");
    }
}
