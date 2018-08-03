package whaapps.courtcounter;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import whaapps.courtcounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    private GameViewModel gameViewModel;
    private int scoreTeamA = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("scoreTeamA", scoreTeamA);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        // start observing changes from observables and react accordingly
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("scoreTeamA")) {
                scoreTeamA = savedInstanceState.getInt("scoreTeamA");
            }
        }
        activityMainBinding.tvTeamAScore.setText(scoreTeamA + "");
        activityMainBinding.tvTeamBScore.setText(gameViewModel.getScoreTeamB());
        // gameViewModel.getScoreTeamA().observe(this, score -> activityMainBinding.tvTeamAScore.setText(score));

        gameViewModel.getGameTitle().observe(this, gameTitle -> activityMainBinding.tvGameTitle.setText(gameTitle));
        gameViewModel.getTeamAName().observe(this, name -> activityMainBinding.tvTeamAName.setText(name));
        gameViewModel.getTeamBName().observe(this, name -> activityMainBinding.tvTeamBName.setText(name));

        // set click callbacks
        activityMainBinding.bTeamAPlus3.setOnClickListener(this);
        activityMainBinding.bTeamAPlus1.setOnClickListener(this);
        activityMainBinding.bTeamBPlus3.setOnClickListener(this);
        activityMainBinding.bTeamBPlus1.setOnClickListener(this);
        activityMainBinding.bReset.setOnClickListener(this);

        // set team names and game title
        gameViewModel.setGameTitle("August 18 Playoff");
        gameViewModel.setTeamAName("Cupcake");
        gameViewModel.setTeamBName("Donut");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bTeamAPlus3:
                scoreTeamA += 3;
                activityMainBinding.tvTeamAScore.setText("" + scoreTeamA);
                // gameViewModel.addToTeamA(3);
                break;
            case R.id.bTeamAPlus1:
                scoreTeamA += 1;
                activityMainBinding.tvTeamAScore.setText("" + scoreTeamA);
                // gameViewModel.addToTeamA(1);
                break;
            case R.id.bTeamBPlus3:
                gameViewModel.addToTeamB(3);
                activityMainBinding.tvTeamBScore.setText(gameViewModel.getScoreTeamB());
                break;
            case R.id.bTeamBPlus1:
                gameViewModel.addToTeamB(1);
                activityMainBinding.tvTeamBScore.setText(gameViewModel.getScoreTeamB());
                break;
            case R.id.bReset:
                gameViewModel.resetScores();
                break;
        }
    }
}
