## prography_6th_android
simple project for testing ability
 
 
 ViewPager를 이용하여3개의 fragment간에 좌우 swipe통해 화면 전환이 가능하게 하였습니다 
 ![calls](https://user-images.githubusercontent.com/46518769/75943205-b8d27000-5ed7-11ea-9d48-0aa3e3b59227.jpg)
 [첫번째 fragment]
![chats](https://user-images.githubusercontent.com/46518769/75943238-cdaf0380-5ed7-11ea-9adf-b22001a977a4.jpg)
 20개의 item을 retrofit
 [2번째 fragment]
 Retrofit turns your HTTP API into a Java interface.
 '''java
 public interface MovieService {
    //https://ghibliapi.herokuapp.com/films
    @GET("films")
    Call<List<MovieList>> getMovies();
}
  '''
  *ServiceGenerator.java 클래스에서 Retofit 클래스 generated implementation of MovieService Interface. 
  
 
 '''java
 retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
  '''
 

 Each Call from the created GitHubService can make a synchronous or asynchronous HTTP request to the remote webserver.

'''java
 Call<List<MovieList>> call = apiInterface.getMovies();
'''
![chats](https://user-images.githubusercontent.com/46518769/75943209-ba039d00-5ed7-11ea-879f-f92f07e1cc59.jpg)
![contatcs](https://user-images.githubusercontent.com/46518769/75943210-ba9c3380-5ed7-11ea-87e1-42a3851ca6ba.jpg)
  [3번째 fragment]

--------------------
 RecyclerView의 아이템을 클릭했을 때 Intent 이용하여 DetailActivity에서 상세 정보를 표시하는 방법
 ![20th Image](https://user-images.githubusercontent.com/46518769/75943658-f4ba0500-5ed8-11ea-9668-f79e09a7727c.jpg)
#에서 주황색으로 표시된 20번째 item을 클릭하면 아래의 새로운 activity가 펼쳐진다.
![detailActivity](https://user-images.githubusercontent.com/46518769/75943664-f5eb3200-5ed8-11ea-9ea0-892c4042d290.jpg)
