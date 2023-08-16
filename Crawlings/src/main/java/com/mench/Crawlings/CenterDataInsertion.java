package com.mench.Crawlings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//지역아동센터 데이터베이스 삽입
public class CenterDataInsertion {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    public CenterDataInsertion(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;

        // MariaDB JDBC 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("마리아디비 드라이버가 로딩되지 않음");
            e.printStackTrace();
        }
    }

    public void insertData(String centername, String centerinform, String centerintro, String centerprogram) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String insertQuery = "INSERT INTO center (center_name, center_inform, center_intro, center_program) VALUES (?, ?, ?, ?)";

            // 크롤링한 데이터를 가져와서 PreparedStatement에 설정 후 실행
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, centername);
                preparedStatement.setString(2, centerinform);
                preparedStatement.setString(3, centerintro);
                preparedStatement.setString(4, centerprogram);

                int rowsInserted = preparedStatement.executeUpdate(); //추가된 행의 수 > 메서드

                if (rowsInserted > 0) {
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("Failed to insert data (possibly due to duplicate value).");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

// SQL쿼리문
//DELETE FROM crawling.center;
//ALTER TABLE crawling.center AUTO_INCREMENT = 1;
