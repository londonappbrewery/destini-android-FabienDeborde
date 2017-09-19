package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import static com.londonappbrewery.destini.R.id.storyTextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView mStoryTextView;
    Button mButtonTop;
    Button mButtonBottom;
    int mStoryIndex = 0;

    private StoryLine[] mStoryLine = new StoryLine[] {
            new StoryLine(R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2),
            new StoryLine(R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2),
            new StoryLine(R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2)
    };

    private StoryEndings[] mStoryEndings = new StoryEndings[] {
            new StoryEndings(R.string.T4_End),
            new StoryEndings(R.string.T5_End),
            new StoryEndings(R.string.T6_End)
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mButtonTop = (Button) findViewById(R.id.buttonTop);
        mButtonBottom = (Button) findViewById(R.id.buttonBottom);

        updateStory();

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceHandler("top");
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceHandler("bottom");
            }
        });

    }

    private void updateStory() {
        mStoryTextView.setText(mStoryLine[mStoryIndex].getStory());
        mButtonTop.setText(mStoryLine[mStoryIndex].getAnswer1());
        mButtonBottom.setText(mStoryLine[mStoryIndex].getAnswer2());
    }

    private void endStory(int endingID) {
        mStoryTextView.setText(mStoryEndings[endingID].getEnding());
        mButtonTop.setVisibility(View.GONE);
        mButtonBottom.setVisibility(View.GONE);
    }

    private void choiceHandler(String userSelection) {
        Log.d(getClass().getName(), userSelection);
        Log.d(getClass().getName(), "Story index: " + mStoryIndex);
        if (mStoryIndex == 0) { // First story
            if (userSelection == "top") {
                mStoryIndex = 2;
            } else if (userSelection == "bottom"){
                mStoryIndex = 1;
            }
            updateStory();
        } else if (mStoryIndex == 1) { // Second Story
            if (userSelection == "top") {
                mStoryIndex = 2;
                updateStory();
            } else if (userSelection == "bottom") {
                endStory(0);
            }
        } else { // Third Story
            if (userSelection == "top") {
                endStory(2);
            } else if (userSelection == "bottom") {
                endStory(1);
            }
        }
    }




}
