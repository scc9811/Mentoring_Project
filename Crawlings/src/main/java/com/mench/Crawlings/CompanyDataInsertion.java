package com.mench.Crawlings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 기업멘토링 데이터베이스 삽입
// 데이터 직접 모음
public class CompanyDataInsertion {

        public static void main(String[] args) throws SQLException {

            String dbUrl = "jdbc:mariadb://localhost:3306/crawling";
            String dbUser = "root";
            String dbPassword = "tladms016*";



            String[] names = {"KTds", "CJ 올리브네트웍스", "러빙핸즈", "한국토지주택공사", "현대차그룹", "서울런", "심지", "포스코에너지"};
            String[] types = {"예체능, IT", "IT", "생활지도", "생활지도, 국영수", "생활지도, 국영수", "국영수, 독서", "자아, 독서", "과학키트"};
            String[] addresses = {"https://www.ktds.com/company/social_01.jsp", "https://www.cjolivenetworks.co.kr/sustainability/contribution/education",
                    "https://lovinghands.or.kr/71", "https://lhggoma.co.kr/", "https://h-jumpschool.kr/", "https://slearn.seoul.go.kr/front/mentoringIntro.do",
                    "https://simjipioneer.modoo.at/?link=4unlke1w", "http://www.poscoenergy.com/_service/pr/news/view.asp?idx=481&no=184&page=1"
            };

            try (Connection dbconnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                String insertQuery1 = "INSERT INTO company (name, type, address) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement1 = dbconnection.prepareStatement(insertQuery1);


                for (int i = 0; i < names.length; i++) {
                    preparedStatement1.setString(1, names[i]);
                    preparedStatement1.setString(2, types[i]);
                    preparedStatement1.setString(3, addresses[i]);
                    preparedStatement1.executeUpdate();
                }
                System.out.println("Data inserted successfully.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}


