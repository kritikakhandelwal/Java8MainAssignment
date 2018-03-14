package edu.knoldus.View;

import edu.knoldus.Model.TwitterOperation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class TwitterApplication {
    public static void main(String[] args) {
        TwitterConn connectionObject = new TwitterConn();
        twitter4j.Twitter twitter = connectionObject.getConnectionTwitter();
        TwitterOperation twitterobject = new TwitterOperation(twitter);
        try {
            System.out.println(" Latest Post (Newer to Older) with limit");
//            CompletableFuture<Stream<String>> resultTweet = twitterobject.getPostWithLimit();
//            resultTweet.thenAccept(tweets -> tweets.forEach(System.out::println));

            System.out.println("Older to Newer with limit and offset values");
//            CompletableFuture<List<Status>> resultOldNew = twitterobject.getPostWithNToO();
//            resultOldNew.thenAccept(tweets -> tweets.forEach(System.out::println));
            System.out.println("Number of Retweets (Higher to Lower)");
//            System.out.println("Number of Retweets (Higher to Lower)");
//            CompletableFuture<List<Status>> resultRetweet = twitterobject.getRePostCount();
//            resultRetweet.thenAccept(tweets -> tweets.forEach(System.out::println));
            System.out.println("Number of Likes (Higher to Lower)");
//
            CompletableFuture<List<Status>> resultTotalLike = twitterobject.getTotalLikes();
            System.out.println(resultTotalLike);
            resultTotalLike.thenAccept(tweets -> tweets.forEach(System.out::println));
            System.out.println("Get the List and number of tweets for an entered date.");

//            CompletableFuture<List<Status>> resultDate = twitterobject.getPostForDate();
//            resultDate.thenAccept(tweets -> tweets.forEach(System.out::println));
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}