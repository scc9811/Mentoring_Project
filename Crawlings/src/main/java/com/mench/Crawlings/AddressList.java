package com.mench.Crawlings;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressList {

    static String baseurl = "https://ion.or.kr"; // 기본 URL
    static String mainPageFormat = baseurl + "/center/search/%d"; // 메인 페이지 URL //for문으로 바꾸기 !!!

    public static void main(String[] args) {

        List<String> addresses = new ArrayList<>();; //메서드 할당

        for (int page = 1; page <= 150; page++) {
            String mainPageUrl = String.format(mainPageFormat, page);
            List<String> pageAddresses = getListOfAddresses(mainPageUrl);
            addresses.addAll(pageAddresses);
        }

//         주소리스트 내용 출력(확인용)
        for (String address : addresses) {
            System.out.println(address);
        }
//        System.out.println(addresses);
    }

    private static String extractAddressFromOnclick(String onclickAttribute) {
        int startIndex = onclickAttribute.indexOf("('/") + 2; // ('/ 문자열 인덱스에서 + 2 = '/'에서 부터 시작하도록.
        int endIndex = onclickAttribute.indexOf("')");
        return onclickAttribute.substring(startIndex, endIndex); //사이의 부분문자열 추출
    }

    public static List<String> getListOfAddresses(String mainPageUrl) {
        List<String> addresses = new ArrayList<>(); // 주소 저장할 리스트

        try {
            Document mainPage = Jsoup.connect(mainPageUrl).get(); // 메인 페이지 크롤링
            Elements linkElements = mainPage.select("a[onclick*=openMinihp]"); // onclick 포함하는 링크 선택

            for (Element linkElement : linkElements) {
                String onclickAttribute = linkElement.attr("onclick"); //속성 값을 가져옴 = openMinihp('/주소값'); 반환
                String address = extractAddressFromOnclick(onclickAttribute);
                addresses.add(address);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addresses;
    }

}


