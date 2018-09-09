package lee.com.mvp_http_example.retrofitintro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {
    //GET
    /*
     query	string	Y	-	검색을 원하는 문자열로서 UTF-8로 인코딩한다.
    display	integer	N	10(기본값), 100(최대)	검색 결과 출력 건수 지정
    start	integer	N	1(기본값), 1000(최대)	검색 시작 위치로 최대 1000까지 가능
    sort	string	N	string	정렬 옵션: sim (유사도순), date (날짜순)
    filter	string	N	all (기본값),large, medium, small	사이즈 필터 옵션: all(전체), large(큰 사이즈), medium(중간 사이즈), small(작은 사이즈)
    */
    @Headers({
            "X-Naver-Client-Id: 9HHE3iUGTmXCstyW4Env",
            "X-Naver-Client-Secret: Jk3P8TG1tU",
            "Accept: application/json"
    })
    @GET("/v1/search/image")
    Call<ImageResponse> NaverImageBySearch(@Query("query") String query ,@Query("display") int display ,@Query("start") int start ,@Query("sort") String sort);
}

