package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enum.Screen;

public class GdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	public int appState = Screen.MAIN_MENU;

	public Sprite closeChest;
    public Sprite openChest;


    public float largeur_ecran;
    public float hauteur_ecran;
    public float xPosCloseChest;
    public float yPosCloseChest;
    public float xPosOpenChest;
    public float yPosOpenChest;

    // FONCTIONS DE CALCUL DE RESOLUTION ===========================================================
    private float xUnite(float x)
    {
        return x*largeur_ecran/480f;
    }

    private float yUnite(float y)
    {
        return y*hauteur_ecran/320;
    }


	// INITIALISATION ==============================================================================
	@Override
	public void create () {
		batch = new SpriteBatch();

        largeur_ecran = Gdx.graphics.getWidth();
        hauteur_ecran = Gdx.graphics.getHeight();

		closeChest = new Sprite(new Texture(Gdx.files.internal("closechest.png")));
		openChest = new Sprite(new Texture(Gdx.files.internal("openchest.png")));

		closeChest.setSize(xUnite(250), yUnite(200));
		openChest.setSize(xUnite(250), yUnite(200));

		xPosCloseChest = xUnite(176);
		yPosCloseChest = yUnite(150);

        xPosOpenChest = xUnite(176);
        yPosOpenChest = yUnite(150);


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
    }

    // DISPLAY =====================================================================================
	// Screens -------------------------------------------------------------------------------------
	public void gameScreenDisp(){

        openChest.setPosition(xPosOpenChest, yPosOpenChest);
        openChest.draw(batch);

	}

	public void mainMenuDisp(){

        closeChest.setPosition(xPosCloseChest, yPosCloseChest);
        closeChest.draw(batch);

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

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (screenX > xUnite(180) && screenX < xUnite(430) && screenY > yUnite(115) && screenY < yUnite(300)) {
                    appState = Screen.GAME_SCREEN;
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });

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
	}
}
