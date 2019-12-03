package com.example.blogreader;

import com.example.blogreader.model.PostList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerAPI {

    private final static String KEY = "";
    private final static String URL = "https://www.googleapis.com/blogger/v3/blogs/5504837196420204908/posts/";

    public static PostService postService = null;

    public static PostService getPostService() {
        if (postService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService {

        //TO get all posts
        @GET("?key=" + KEY)
        Call<PostList> getPostList();

        //To get postById
//        @GET("/{postId}?key="+KEY)
//        Call<Item> getPostById(@Path("postId") String id);
    }

}
