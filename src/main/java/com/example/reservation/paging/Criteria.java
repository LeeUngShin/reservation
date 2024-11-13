package com.example.reservation.paging;

import lombok.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Criteria {
    private int currentPageNo;
    private int recordsPerPage;
    private int pageSize;
    //private String searchKeyword;
    //private String searchType;
    private String bigRegion;
    private String smallRegion;
    private String indoorOutdoor;

    public Criteria() {
        this.currentPageNo = 1;
        this.recordsPerPage = 10;
        this.pageSize = 10;
    }

    public String makePagingQueryString(int pageNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("currentPageNo", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("pageSize", pageSize)
                .build().encode();
        System.out.println(">>>>>>>>>>"+uriComponents.toUriString());
        return uriComponents.toUriString();
    }

//    public String makeSearchQueryString(int pageNo) {
//        UriComponents uriComponents = UriComponentsBuilder.newInstance()
//                .queryParam("currentPageNo", pageNo)
//                .queryParam("recordsPerPage", recordsPerPage)
//                .queryParam("pageSize", pageSize)
//                .queryParam("searchType", searchType)
//                .queryParam("searchKeyword", searchKeyword)
//                .build().encode();
//        System.out.println(">>>>>>>>>>"+uriComponents.toUriString());
//        return uriComponents.toUriString();
//    }

    public String makeChoiceQueryString(int pageNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("currentPageNo", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("pageSize", pageSize)
                .queryParam("bigRegion", bigRegion)
                .queryParam("smallRegion", smallRegion)
                .queryParam("indoorOutdoor", indoorOutdoor)
                .build().encode();
        System.out.println(">>>>>>>>>>"+uriComponents.toUriString());
        return uriComponents.toUriString();
    }

    public String makeChoice2QueryString(int pageNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("currentPageNo", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("pageSize", pageSize)
                .queryParam("bigRegion", bigRegion)
                .queryParam("smallRegion", smallRegion)
                .build().encode();
        System.out.println(">>>>>>>>>>"+uriComponents.toUriString());
        return uriComponents.toUriString();
    }
}

