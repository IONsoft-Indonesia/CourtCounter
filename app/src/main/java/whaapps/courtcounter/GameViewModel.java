package whaapps.courtcounter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

/**
 * @author hendrawd on 28/07/18
 */
public class GameViewModel extends ViewModel {
    private MutableLiveData<String> gameTitle;
    private MutableLiveData<String> teamAName;
    private MutableLiveData<String> teamBName;

    private MutableLiveData<Integer> scoreTeamA;
    private int scoreTeamB;

    private LiveData<String> scoreStringTeamA;

    public GameViewModel() {
        scoreTeamA = new MutableLiveData<>();
        scoreTeamA.setValue(0);
        // can transform integer to string or use mutable live data directly
        scoreStringTeamA = Transformations.map(scoreTeamA, String::valueOf);

        gameTitle = new MutableLiveData<>();
        teamAName = new MutableLiveData<>();
        teamBName = new MutableLiveData<>();
    }

    public void addToTeamA(int amount) {
        if (scoreTeamA.getValue() != null) {
            scoreTeamA.setValue(scoreTeamA.getValue() + amount);
        }
    }

    public LiveData<String> getScoreTeamA() {
        return scoreStringTeamA;
    }

    public void addToTeamB(int amount) {
        scoreTeamB += amount;
    }

    public String getScoreTeamB() {
        return scoreTeamB + "";
    }

    public void setTeamAName(final String name) {
        teamAName.setValue(name);
    }

    public LiveData<String> getTeamAName() {
        return teamAName;
    }

    public void setTeamBName(final String name) {
        teamBName.setValue(name);
    }

    public LiveData<String> getTeamBName() {
        return teamBName;
    }

    public void setGameTitle(final String title) {
        gameTitle.setValue(title);
    }

    public LiveData<String> getGameTitle() {
        return gameTitle;
    }

    public void resetScores() {
        scoreTeamA.setValue(0);
        scoreTeamB = 0;
    }
}
