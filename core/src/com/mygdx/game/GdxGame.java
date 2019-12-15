package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enum.Screen;

public class GdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	public int appState = Screen.MAIN_MENU;


	// INITIALISATION ==============================================================================
	@Override
	public void create () {
		batch = new SpriteBatch();

	}


	// MAIN ========================================================================================
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		switch(appState){
            case Screen.MAIN_MENU:
                mainMenu();
				break;
			case Screen.GAME_SCREEN:
			    gameScreen();
			    break;
            case Screen.GAME_PAUSE:
                gamePause();
                break;
            case Screen.GAME_END:
                gameEnd();
                break;
        }

		batch.end();
	}




	// Main menu -----------------------------------------------------------------------------------
    public void mainMenu(){
        //Gérer les inputs du menu
        mainMenuInput();
        //Gérer l'affichage du menu
        mainMenuDisp();
    }

    // Game screen ---------------------------------------------------------------------------------
    public void gameScreen(){
        //Gérer les inputs du jeu
        gameScreenInput();
        //Gérer l'affichage du jeu
        gameScreenDisp();
    }

    // Main menu -----------------------------------------------------------------------------------
    public void gamePause(){
        //Gérer les inputs du menu de pause
        gamePauseInput();
        //Gérer l'affichage du menu de pause
        gameScreenDisp(); // Afficher d'abord l'écran du jeu
        gamePauseDisp(); // Rajouter le menu de pause par dessus
    }

    // Game screen ---------------------------------------------------------------------------------
    public void gameEnd(){
        //Gérer les inputs du menu de fin de partie
        gameEndInput();
        //Gérer l'affichage du menu de fin de partie
        gameScreenDisp(); // Afficher d'abord l'écran du jeu
        gameEndDisp(); // Rajouter le menu de fin jeu par dessus
        //heyyyyyy
    }

    // DISPLAY =====================================================================================
	// Screens -------------------------------------------------------------------------------------
	public void gameScreenDisp(){

	}

	public void mainMenuDisp(){

	}

    public void gamePauseDisp(){

    }

    public void gameEndDisp(){

    }

    // INPUTS ======================================================================================
    // Screens -------------------------------------------------------------------------------------
    public void gameScreenInput(){

    }

    public void mainMenuInput(){

    }

    public void gamePauseInput(){

    }

    public void gameEndInput(){

    }

    // DESTRUCTION =================================================================================
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		//test
	}
}
