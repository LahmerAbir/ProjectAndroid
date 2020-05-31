package com.example.tounsia.English.Forum;

import com.google.firebase.firestore.Exclude;

import io.reactivex.annotations.NonNull;

public class BlogPostId {
    //This BlogPostId is used in implementing the BlogPost timestamp

    @Exclude
    public  String BlogPostId;

    public <T extends com.example.tounsia.English.Forum.BlogPostId> T withId(@NonNull final String id){
        this.BlogPostId = id;
        return (T) this;
    }

}
