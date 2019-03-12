//Linda Mitton ~ March 11, 2019
package com.example.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //create a game instance for playing
    Rochambo game = new Rochambo();
    //create the views to hold the game results
    private ImageView playerImage;
    private ImageView gameImage;
    private TextView gameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the views to point to the correct display view
        playerImage = findViewById(R.id.player_move);
        gameImage = findViewById(R.id.game_move);
        gameResult = findViewById(R.id.results_text);
    }

    /**
     * Pass scissors selection into the game
     *
     * @param view
     */
    public void scissorsPlayed(View view) {
        game.playerMakesMove(game.SCISSOR);
        //set the player image based on the selection
        playerImage.setImageResource(R.drawable.scissors);
        //display the results of the game
        displayResults(game.winLoseOrDraw());
    }

    /**
     * Pass paper selection into the game
     *
     * @param view
     */
    public void paperPlayed(View view) {
        game.playerMakesMove(game.PAPER);
        //set the player image based on the selection
        playerImage.setImageResource(R.drawable.paper);
        //display the results of the game
        displayResults(game.winLoseOrDraw());
    }

    /**
     * Pass rock selection into the game
     *
     * @param view
     */
    public void rockPlayed(View view) {
        game.playerMakesMove(game.ROCK);
        //set the player image based on the selection
        playerImage.setImageResource(R.drawable.rock);
        //display the results of the game
        displayResults(game.winLoseOrDraw());
    }

    /**
     * Set the image the game picked and update the text field
     * @param winLoseOrDraw
     */
    private void displayResults(int winLoseOrDraw) {
        //set the game image based
        if (game.getGameMove() == game.ROCK) {
            gameImage.setImageResource(R.drawable.rock);
        }else if (game.getGameMove() == game.PAPER){
            gameImage.setImageResource(R.drawable.paper);
        }else{
            gameImage.setImageResource(R.drawable.scissors);
        }

        //create an animator for the player image
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(playerImage,
                "rotationX", 0f, 360f)
                .setDuration(500);

        //create an animator for the game image
        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(gameImage,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        //animate the two images
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();

        //update the textview with the results of the game
        gameResult.setText(winLoseOrDraw);
    }


}
