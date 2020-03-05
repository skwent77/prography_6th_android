
=======
# prography_6th_android 사전과제


### To Run this project
----
   #### first, git clone this file to your repository/ download Zip File and import the project
       we run this file on gpu and you should check server address. 
       
   #### second, build and run the app
    in some version of Android Studio , version problems can occur
    gradle plugin version:3.6.1//compile SDK Version 29(API 29:Android 10.0(Q))
      
  [참고 자료]
  1. https://github.com/square/okhttp
  2. https://square.github.io/retrofit/
  3. https://github.com/google/gson/blob/master/UserGuide.md
----
###구현 화면 간단 요약
   <div>
   <img width="250" src="https://user-images.githubusercontent.com/46518769/75943205-b8d27000-5ed7-11ea-9d48-0aa3e3b59227.jpg" " hspace="20" >
   <img width="250" src="https://user-images.githubusercontent.com/46518769/75943238-cdaf0380-5ed7-11ea-9adf-b22001a977a4.jpg" hspace="20">
   <img width="250" src="https://user-images.githubusercontent.com/46518769/75943210-ba9c3380-5ed7-11ea-87e1-42a3851ca6ba.jpg" hspace="20">
   </div>


----
----
 ###  구현 설명
  
 1. 화면 구성
  - MainActivity
  1) ViewPager는 페이지 전환을 위한 스와이프 동작이 내장되어 있고, 그래서 3개의 fragment간에 화면 전환이 가능합니다.  
  2) BottomNavigationView의 메뉴를 클릭하였을 때, 선택한 메뉴 아이템의 리소스 id를 인자로 받고 ViewPager의 setCurrentItem() 메소드를 통하여 화면을 구성합니다.
 - Fragment를 처리하는 PagerAdapter는 두 가지 Class가 존재한다. 하나는 FragmentPagerAdapter 이고 다른 하나는 FragmentStatePagerAdapter
 
 2. 네트워크 통신 
 - OkHttp3를 통하여 url에서 데이터를 가져온다. url에 JSON 정보가 담겨있다. response를 이용해 url속 JSON정보를 받아온다. 
 - HTTP통신을 지원하는 라이브러리 중 Retrofit을 사용하였다. Retrofit을 사용하기 위하여 MoveService interface을 정의하고, 이 interface class을 Retrofit에 초기화를 하는 과정을 거쳤습니다.
 - retrofit은 json응답을 간단하게 변환할 수 있도록 다양한 Converter를 제공하고 있으며 addConverterFactory()메소드를 통해 이루어집니다. Gson을 이용하기 위해서 GsonConverterFactory를 추가했습니다
  
----
### 상세하게 화면 뜯어보기
<div>
<img src="https://user-images.githubusercontent.com/46518769/75956482-5b501a80-5efb-11ea-8ddf-ce0c1c39089d.PNG" width="50%" height="40%">
</img>
</div>






 #### [MainActivity 순수 화면]
 
 - ViewPager와 BottomNavigationView 2개가 구현되어 있는 상태

 
 #### ![calls](https://user-images.githubusercontent.com/46518769/75943205-b8d27000-5ed7-11ea-9d48-0aa3e3b59227.jpg)
 
 
 #### [1번째 fragment]

 
 
 
![chats](https://user-images.githubusercontent.com/46518769/75943238-cdaf0380-5ed7-11ea-9adf-b22001a977a4.jpg)
 
 
 
 
 # [2번째 fragment]
 
 - 20개의 item을 Retrofit을 사용하여 데이터 받아온 후에 chatsFragment에 번호,제목,감독,출시년도 나열
 - androidx 의 RecyclerView와 CardView 이용하여 위아래로 스크롤 가능한 Fragment를 만들었습니다
 <br>
 Retrofit turns your HTTP API into a Java interface.
 ```java
 public interface ApiService {
    //https://ghibliapi.herokuapp.com/films
    @GET("films")
    Call<List<MovieList>> getMovies();
}
  ```
  **ServiceGenerator.java** 클래스에서 Retofit 클래스 generated implementation of ApiService Interface. 
  
 
 ```java
 
 retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
 
 ```
- Retrofit은 REST API을 구현한 상태입니다.
  <br>
  그래서 간단하게 GET, POST, PUT, DELETED 등을 전달하면 서버로부터 xml, json으로 응답을 전달받습니다. 
- Gson을 사용하는 GitHubService 인터페이스가 Gson을 역직렬화가 가능하게 GsonConverterFactory 클래스를 통해 컨버터를 추가하는 코드

 > Each Call from the created GitHubService can make a synchronous or asynchronous HTTP request to the remote webserver.
         -https://square.github.io/retrofit/
```java
 
 Call<List<MovieList>> call = apiInterface.getMovies();
```
 
![chats](https://user-images.githubusercontent.com/46518769/75943209-ba039d00-5ed7-11ea-879f-f92f07e1cc59.jpg)

![contatcs](https://user-images.githubusercontent.com/46518769/75943210-ba9c3380-5ed7-11ea-87e1-42a3851ca6ba.jpg)
 
# [3번째 fragment]

---
#### RecyclerView의  아이템을  클릭했을  때  Intent  이용하여  DetailActivity에서  상세  정보를  표시하는  방법
 ![20th Image](https://user-images.githubusercontent.com/46518769/75943658-f4ba0500-5ed8-11ea-9668-f79e09a7727c.jpg)
 
#### 위 사진 에서 주황색으로 표시된 20번째 item을 클릭하면 아래 마지막 사진(When Marnie Was There)의 새로운 DetailActivity가 펼쳐진다. 
Intent에 putExtra() 이용해서 정보를 전송
#### MovieAdapter.java에서 정보 전송
 ```java
 itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* DetailActivity intent 호출 */
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    int pos = getAdapterPosition();
                    intent.putExtra("data_num", movies.get(pos).getNum());
                    intent.putExtra("data_title", movies.get(pos).getTitle());
                    intent.putExtra("data_description", movies.get(pos).getDescription());
                    intent.putExtra("data_director", movies.get(pos).getDirector());
                    intent.putExtra("data_producer", movies.get(pos).getProducer());
                    intent.putExtra("data_releasedate", movies.get(pos).getReleaseDate());
                    intent.putExtra("data_rtscore", movies.get(pos).getRt_score());
                    itemView.getContext().startActivity(intent);
                }
            });
```
##### DetailActivity.java에서 정보를 표시
```java 
 Intent newintent = getIntent();
        detail_title.setText(newintent.getExtras().getString("data_title"));
        detail_description.setText(newintent.getExtras().getString("data_description"));
        detail_director.setText("Directed By " + newintent.getExtras().getString("data_director"));
        detail_producer.setText("Produced By " + newintent.getExtras().getString("data_producer"));
        detail_releasedate.setText(newintent.getExtras().getString("data_releasedate"));
        detail_rtscore.setText("Score: " + String.valueOf(newintent.getExtras().getInt("data_rtscore")) + " / 100");

```

![detailActivity](https://user-images.githubusercontent.com/46518769/75943664-f5eb3200-5ed8-11ea-9ea0-892c4042d290.jpg)
>>>>>>> c0d8ad81b9303fb9ec79039d27fe574f7d217470
