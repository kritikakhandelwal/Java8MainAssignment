package edu.knoldus.Model;

import twitter4j.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TwitterOperation {
    private Twitter twitter;

    public TwitterOperation(Twitter twitter) {
        this.twitter = twitter;
    }

    public CompletableFuture<Stream<String>> getPostWithLimit() {
        return CompletableFuture.supplyAsync(() -> {
            Stream<String> tweetData = null;
            try {
                String hashTag = "#IncredibleIndia";
                Integer count = 100;
                Query query = new Query(hashTag);
                query.setCount(count);
                query.resultType(Query.RECENT);
                QueryResult result;
                do {
                    result = twitter.search(query);
                    List<Status> tweets = result.getTweets();
                    System.out.println(tweets);
                    tweetData = tweets.stream().map(tweet -> {
                        System.out.println(tweet.getText());
                        return tweet.getText();
                    });
                    System.out.println(tweetData);
                    return tweetData;
                } while ((query = result.nextQuery()) != null);
            } catch (TwitterException te) {
                System.out.println(te.getMessage());
            }
            return tweetData;
        });
    }

    public CompletableFuture<List<Status>> getPostWithNToO() {
        return CompletableFuture.supplyAsync(() -> {
            List<Status> latestTweets = new ArrayList<>();
            try {
                String hashTag = "#IncredibleIndia";
                Integer count = 100;
                Query query = new Query(hashTag);
                query.setCount(count);
                query.resultType(Query.RECENT);
                QueryResult result = this.twitter.search(query);
                result.getTweets().sort(Comparator.comparing(status ->
                        status.getCreatedAt().getTime()));
                latestTweets.addAll(result.getTweets());
            } catch (TwitterException e) {
                System.out.println("Error message " + e.getMessage());
            }
            return latestTweets;
        });
    }
//    public CompletableFuture<List<Status>> getRePostCount() {
//        return CompletableFuture.supplyAsync(() -> {
//            List<Status> latestTweets = new ArrayList<>();
//            try {
//                String hashTag = "#IncredibleIndia";
//                Integer count = 100;
//                Query query = new Query(hashTag);
//                query.setCount(count);
//                query.resultType(Query.RECENT);
//                    QueryResult queryResult = this.twitter.search(query);
//                    queryResult.getTweets().sort((statusFirst, statusSecond) ->
//                            statusSecond.getRetweetCount() - statusFirst.getRetweetCount());
//                    latestTweets.addAll(queryResult.getTweets());
//                } catch (TwitterException e) {
//                    System.out.println("Error Message " + e.getMessage());
//                }
//                return latestTweets;
//            });
//}

    public CompletableFuture<List<Status>> getRePostCount() {
        return CompletableFuture.supplyAsync(() -> {
            List<Status> higherToLowTweets = Collections.emptyList();
            try {
                higherToLowTweets = twitter.getHomeTimeline().stream()
                        .sorted(Comparator.comparing(Status::getRetweetCount).reversed())
                        .collect(Collectors.toList());
            } catch (TwitterException twitterException) {
                twitterException.printStackTrace();
            }

            return higherToLowTweets;
        });
    }
    public CompletableFuture<List<Status>> getTotalLikes() {
        return CompletableFuture.supplyAsync(() -> {
            List<Status> latestTweets = new ArrayList<>();
            try {
                String hashTag = "#IncredibleIndia";
                Integer count = 100;
                Query query = new Query(hashTag);
                query.setCount(count);
                query.resultType(Query.RECENT);
                QueryResult queryResult = this.twitter.search(query);
                queryResult.getTweets().sort((firstStatus, secondStatus) ->
                        secondStatus.getFavoriteCount() - firstStatus.getFavoriteCount());
                latestTweets.addAll(queryResult.getTweets());
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return latestTweets;
        });
    }

    public CompletableFuture<List<Status>> getPostForDate() {
        return CompletableFuture.supplyAsync(() -> {
            List<Status> latestTweets = new ArrayList<>();
            try {
                String hashTag = "#IncredibleIndia";
                String date = "2018-03-11";
                Integer count = 100;
                Query query = new Query(hashTag);
                query.setUntil(date);
                query.setCount(count);
                query.resultType(Query.RECENT);
                QueryResult queryResult = this.twitter.search(query);
                latestTweets.addAll(queryResult.getTweets());
                System.out.println("Number of tweets on given date are : " + latestTweets.size());
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return latestTweets;
        });
    }


}