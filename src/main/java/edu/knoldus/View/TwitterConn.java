
package edu.knoldus.View;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterConn {
    /**
     * @return twitter . Twitter object
     */

    public Twitter getConnectionTwitter() {
        Config conf = ConfigFactory.load();
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(conf.getString("consumerKey"),
                conf.getString("consumerSecretKey"));
        twitter.setOAuthAccessToken(new AccessToken(
                conf.getString("accessToken"),
                conf.getString("accessSecretToken")));
        return twitter;
    }
}