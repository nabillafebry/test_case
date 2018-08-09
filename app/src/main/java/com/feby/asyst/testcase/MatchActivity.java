package com.feby.asyst.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.feby.asyst.testcase.fragment.MatchFragment;
import com.feby.asyst.testcase.utility.Constant;

public class MatchActivity extends AppCompatActivity implements MatchFragment.OnButtonNextClickListener {

    String teamAName, teamBName;
    int quarter = 1, scoreTeamA = 0, scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        if (getIntent().getExtras() != null) {

            teamAName = getIntent().getExtras().getString(Constant.KEY_TEAM_A_NAMA);
            teamBName = getIntent().getExtras().getString(Constant.KEY_TEAM_B_NAMA);
        }

        loadFragment();
    }

    @Override
    public void onButtonNextClikListener(int scoreTeamA, int scoreTeamB) {
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;

        ++quarter;

        if (quarter <= 4) {
            loadFragment();
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            int winnerScore = 0;
            String winnerName;

            if (scoreTeamA > scoreTeamB) {
                winnerName = teamAName;
                winnerScore = scoreTeamA;
            } else if (scoreTeamA < scoreTeamB) {
                winnerName = teamBName;
                winnerScore = scoreTeamB;
            } else {
                winnerName = "DRAW";
            }

            intent.putExtra(Constant.KEY_WINNER_NAME, winnerName);
            intent.putExtra(Constant.KEY_WINNER_SCORE, winnerScore);

            startActivity(intent);
        }

    }

    private void loadFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MatchFragment matchFragment = MatchFragment.newInstance(teamAName, teamBName, quarter, scoreTeamA, scoreTeamB);

        transaction.add(R.id.fragment_container, matchFragment);
        transaction.commit();
    }
}
