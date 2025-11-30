package design;

import java.util.*;

/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId.
 Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed.
Each item in the news feed must be posted by users who the user followed or by the user themself.
Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user
 with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing
the user with ID followeeId.


Example 1:

Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 */
class Twitter {

    //Used to hold the list of tweets in time based order
    HashMap<Integer, Post> userposts;
    HashMap<Integer, HashSet<Integer>> follows;
    private static int time = 0;

    private class Post {
        int userId;
        int id;
        int time;
        Post next;

        Post(int userId, int id, int time) {
            this.userId = userId;
            this.id = id;
            this.time = time;
            next = null;
        }
    }

    public Twitter() {
        userposts = new HashMap<>();
        follows = new HashMap<>();

    }

    public void postTweet(int userId, int tweetId) {
        //Create a post object
        Post currentTweet = new Post(userId, tweetId, time++);

        //Put the current tweet to the head of the list.
        currentTweet.next = userposts.getOrDefault(userId, null);
        userposts.put(userId, currentTweet);

    }

    public List<Integer> getNewsFeed(int userId) {
        //get list of followes. Use the new function because we dont want to mutate the original list by adding user Id to it
        Set<Integer> followees = new HashSet<>(follows.getOrDefault(userId, new HashSet<>()));
        ;
        followees.add(userId);

        //Define result object
        List<Integer> result = new ArrayList<>();

        //Define a max heap that sorts based on time
        PriorityQueue<Post> pq = new PriorityQueue<>((a, b) -> b.time - a.time);

        //Add head for all the valid users.
        for (int fId : followees) {
            if (userposts.containsKey(fId) && userposts.get(fId) != null) {
                pq.add(userposts.get(fId));
            }

        }

        //Compute result
        while (!pq.isEmpty() && result.size() < 10) {
            Post addedPost = pq.poll();
            if (addedPost.next != null)
                pq.add(addedPost.next);
            result.add(addedPost.id);
        }

        return result;

    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> followees = follows.getOrDefault(followerId, new HashSet<>());
        followees.add(followeeId);
        // This statement is needed because getOrDefault creates a temporary map if key not present.
        follows.put(followerId, followees);
    }

    public void unfollow(int followerId, int followeeId) {
        //you cant follow yourself.
        if (followeeId == followerId)
            return;
        if (follows.containsKey(followerId)) {
            Set<Integer> followees = follows.get(followerId);
            followees.remove(followeeId);
        }

    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        twitter.postTweet(1, 5);
        System.out.println("NewsFeed(1): " + twitter.getNewsFeed(1)); // [5]

        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println("NewsFeed(1): " + twitter.getNewsFeed(1)); // [6, 5]

        twitter.unfollow(1, 2);
        System.out.println("NewsFeed(1): " + twitter.getNewsFeed(1)); // [5]
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
