package com.feby.asyst.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.feby.asyst.testcase.utility.Constant;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvWinnerName, tvWinnerScore;
    Button buttonBack;
    String winnerName;
    int winnerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvWinnerName = findViewById(R.id.winner_team_textview);
        tvWinnerScore = findViewById(R.id.winner_team_score_textview);
        buttonBack = findViewById(R.id.back_button);

        buttonBack.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            winnerName = getIntent().getExtras().getString(Constant.KEY_WINNER_NAME);
            winnerScore = getIntent().getExtras().getInt(Constant.KEY_WINNER_SCORE);
        }

        tvWinnerName.setText(winnerName + "");
        tvWinnerScore.setText(winnerScore + "");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
