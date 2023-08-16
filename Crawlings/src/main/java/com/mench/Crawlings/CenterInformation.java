package com.mench.Crawlings;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;

//지역아동센터 정보 크롤링

public class CenterInformation {

    public static void main(String[] args) {

        String dbUrl = "jdbc:mariadb://localhost:3306/crawling";
        String dbUser = "root";
        String dbPassword = "tladms016*";
        CenterDataInsertion dataInsertion = new CenterDataInsertion(dbUrl, dbUser, dbPassword);

        String baseurl = "https://ion.or.kr";

        //459(459*10) max인데 디비에서 100까지만 가능(100*10)
        for (int page = 1; page <= 150; page++) {
            String mainPageUrl = String.format(AddressList.mainPageFormat, page);
            List<String> addresses = AddressList.getListOfAddresses(mainPageUrl);

            for (String address : addresses) {
                String URL1 = baseurl + address;
                String URL2 = URL1 + "/content/intro";
                String URL3 = URL1 + "/content/program";

                try {
                    Document doc_url1 = Jsoup.connect(URL1).get();
                    Elements contentElements = doc_url1.select("div.centerInfo"); // div.centerInfo 요소들 선택

                    // 1. 센터이름 출력
                    Element strongElement = contentElements.select("strong").first();
                    String centername = strongElement.text();

                    // 2. 주소-전화번호-이용시간
                    Elements spanElements = contentElements.select("span");
                    String centerinform = spanElements.text();

                    // 3. 센터 소개

                    Document doc_url2 = Jsoup.connect(URL2).get();
                    Elements introElements = doc_url2.select("div.miniContentArea");
                    Elements inpElements = introElements.select("p");

                    String centerintro = ""; // 초기화

                    if(inpElements.size() != 0){
                        centerintro = inpElements.text();
                    } else {
                        Elements i_defaultElements = doc_url2.select("div.defaultInfo");
                        centerintro = i_defaultElements.text();
                    }


                    // 4. 센터 프로그램
                    Document doc_url3 = Jsoup.connect(URL3).get();
                    Elements proElements = doc_url3.select("div.miniContentArea");
                    Elements ppElements = proElements.select("p");

                    String centerprogram = ""; // 초기화
                    if(ppElements.size() != 0){
                        centerprogram = ppElements.text();
                    } else {
                        Elements p_defaultElements = doc_url3.select("div.defaultInfo");
                        centerprogram = p_defaultElements.text();
                    }

                    // 데이터베이스에 삽입
                    dataInsertion.insertData(centername,  centerinform, centerintro, centerprogram);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

