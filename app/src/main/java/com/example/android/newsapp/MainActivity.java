package com.example.android.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // The request url
    private static String REQUEST_URI = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    private static String TEST_JSON = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":11668,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1167,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"commentisfree/2017/oct/03/the-guardian-view-on-the-tories-and-brexit-debate-what-debate\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2017-10-03T18:02:47Z\",\"webTitle\":\"The Guardian view on the Tories and Brexit: Debate? What debate? | Editorial\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2017/oct/03/the-guardian-view-on-the-tories-and-brexit-debate-what-debate\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2017/oct/03/the-guardian-view-on-the-tories-and-brexit-debate-what-debate\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"world/2017/sep/24/violence-against-women-transgender-debate\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2017-09-24T17:04:44Z\",\"webTitle\":\"Violence has no place in transgender debate | Letters\",\"webUrl\":\"https://www.theguardian.com/world/2017/sep/24/violence-against-women-transgender-debate\",\"apiUrl\":\"https://content.guardianapis.com/world/2017/sep/24/violence-against-women-transgender-debate\",\"isHosted\":false},{\"id\":\"politics/2017/nov/03/mps-to-debate-bill-to-reduce-voting-age-to-16\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-11-03T07:00:17Z\",\"webTitle\":\"MPs to debate bill to reduce voting age to 16\",\"webUrl\":\"https://www.theguardian.com/politics/2017/nov/03/mps-to-debate-bill-to-reduce-voting-age-to-16\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/nov/03/mps-to-debate-bill-to-reduce-voting-age-to-16\",\"isHosted\":false},{\"id\":\"society/2017/oct/23/labour-and-rebel-tories-secure-debate-on-universal-credit-rollout\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2017-10-23T17:47:02Z\",\"webTitle\":\"Labour and rebel Tories secure debate on universal credit rollout\",\"webUrl\":\"https://www.theguardian.com/society/2017/oct/23/labour-and-rebel-tories-secure-debate-on-universal-credit-rollout\",\"apiUrl\":\"https://content.guardianapis.com/society/2017/oct/23/labour-and-rebel-tories-secure-debate-on-universal-credit-rollout\",\"isHosted\":false},{\"id\":\"politics/2017/aug/13/abusive-brexit-debate-insults-our-intelligence\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-08-13T18:29:26Z\",\"webTitle\":\"Abusive Brexit debate insults our intelligence | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2017/aug/13/abusive-brexit-debate-insults-our-intelligence\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/aug/13/abusive-brexit-debate-insults-our-intelligence\",\"isHosted\":false},{\"id\":\"politics/2017/oct/12/brexit-talks-at-disturbing-deadlock-over-divorce-bill-says-eu-negotiator\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-10-12T19:59:22Z\",\"webTitle\":\"EU withdrawal bill debate postponed as Brexit talks hit buffers\",\"webUrl\":\"https://www.theguardian.com/politics/2017/oct/12/brexit-talks-at-disturbing-deadlock-over-divorce-bill-says-eu-negotiator\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/oct/12/brexit-talks-at-disturbing-deadlock-over-divorce-bill-says-eu-negotiator\",\"isHosted\":false},{\"id\":\"politics/2017/oct/09/shouting-the-other-side-down-wont-advance-the-brexit-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-10-09T17:56:41Z\",\"webTitle\":\"Shouting the other side down wonâ€™t advance the Brexit debate | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2017/oct/09/shouting-the-other-side-down-wont-advance-the-brexit-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/oct/09/shouting-the-other-side-down-wont-advance-the-brexit-debate\",\"isHosted\":false},{\"id\":\"politics/2017/sep/25/labour-hostility-eu-tony-benn-intolerance\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-09-25T17:47:42Z\",\"webTitle\":\"Plenty for the Labour party to debate openly | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2017/sep/25/labour-hostility-eu-tony-benn-intolerance\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/sep/25/labour-hostility-eu-tony-benn-intolerance\",\"isHosted\":false},{\"id\":\"media/2017/sep/25/dont-knock-changeorgs-role-in-the-uber-debate\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2017-09-25T17:45:12Z\",\"webTitle\":\"Donâ€™t knock Change.orgâ€™s role in the Uber debate | Letters\",\"webUrl\":\"https://www.theguardian.com/media/2017/sep/25/dont-knock-changeorgs-role-in-the-uber-debate\",\"apiUrl\":\"https://content.guardianapis.com/media/2017/sep/25/dont-knock-changeorgs-role-in-the-uber-debate\",\"isHosted\":false},{\"id\":\"politics/2017/jul/12/labour-attacks-tories-over-smear-campaign-before-abuse-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-07-12T08:46:46Z\",\"webTitle\":\"Labour attacks Tories over 'smear' campaign before abuse debate\",\"webUrl\":\"https://www.theguardian.com/politics/2017/jul/12/labour-attacks-tories-over-smear-campaign-before-abuse-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/jul/12/labour-attacks-tories-over-smear-campaign-before-abuse-debate\",\"isHosted\":false}]}}";

    private ArrayList<NewsArticle> newsArticleList = new ArrayList<NewsArticle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the listView
        ListView newsList = (ListView) findViewById(R.id.list_view);

        // Extract items from the sample JSON
        newsArticleList = QueryUtils.extractNewsArticles(TEST_JSON);

    }
}