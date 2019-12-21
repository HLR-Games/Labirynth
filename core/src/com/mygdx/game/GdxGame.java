package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enum.Screen;

public class GdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	public int appState = Screen.MAIN_MENU;

	public BitmapFont font;

	public Sprite btnPlay;
	public Sprite btnCollection;
	public Sprite btnLeaderboards;
	public Sprite btnPause;
	public Sprite btnQuit;

    public Sprite btnPlayClicked;
    public Sprite btnCollectionClicked;
    public Sprite btnLeaderboardsClicked;
    public Sprite btnPauseClicked;
    public Sprite btnQuitClicked;

    public float largeur_ecran;
    public float hauteur_ecran;

	public float xposBtnPlay;
	public float yposBtnPlay;
    public float xposBtnCollection;
    public float yposBtnCollection;
    public float xposBtnLeaderboards;
    public float yposBtnLeaderboards;
    public float xposBtnPause;
    public float yposBtnPause;
    public float xposBtnQuit;
    public float yposBtnQuit;

    public boolean cliqueBtnPlay;
    public boolean cliqueBtnCollection;
    public boolean cliqueBtnLeaderboards;
    public boolean cliqueBtnPause;
    public boolean cliqueBtnQuit;

    public int page;



	// FONCTIONS DE CALCUL DE RESOLUTION ===========================================================
    private float xUnite(float x)
    {
        return x*largeur_ecran/480f;
    }

    private float yUnite(float y)
    {
        return y*hauteur_ecran/320f;
    }

    // INITIALISATION ==============================================================================
	@Override
	public void create () {
		batch = new SpriteBatch();

        // Obtenir la taille de l'écran actuelle
        largeur_ecran = Gdx.graphics.getWidth();
        hauteur_ecran = Gdx.graphics.getHeight();


		btnPlay = new Sprite(new Texture(Gdx.files.internal("playButton.png")));
		btnCollection = new Sprite(new Texture(Gdx.files.internal("collection.png")));
		btnLeaderboards = new Sprite(new Texture(Gdx.files.internal("leaderboards.png")));
        btnPause = new Sprite(new Texture(Gdx.files.internal("Pause.png")));
        btnQuit = new Sprite(new Texture(Gdx.files.internal("Quit.png")));


        btnPlayClicked = new Sprite(new Texture(Gdx.files.internal("boutonclique.png")));
        btnCollectionClicked = new Sprite(new Texture(Gdx.files.internal("boutonclique.png")));
        btnLeaderboardsClicked = new Sprite(new Texture(Gdx.files.internal("boutonclique.png")));
        btnPauseClicked = new Sprite(new Texture(Gdx.files.internal("Resume.png")));


        btnPlay.setSize(xUnite(128), yUnite(64));
		btnCollection.setSize(xUnite(128), yUnite(64));
		btnLeaderboards.setSize(xUnite(128), yUnite(64));
        btnPause.setSize(xUnite(32), yUnite(32));
        btnQuit.setSize(xUnite(64), yUnite(64));

        btnPlayClicked.setSize(xUnite(64), yUnite(64));
        btnCollectionClicked.setSize(xUnite(128), yUnite(64));
        btnLeaderboardsClicked.setSize(xUnite(128), yUnite(64));
        btnPauseClicked.setSize(xUnite(32), yUnite(32));

        xposBtnPlay = xUnite(176);
		yposBtnPlay = yUnite(223);

        xposBtnCollection = xUnite(176);
        yposBtnCollection = yUnite(150);

        xposBtnLeaderboards = xUnite(176);
        yposBtnLeaderboards = yUnite(74);

        xposBtnPause = xUnite(448);
        yposBtnPause = yUnite(288);

        xposBtnQuit = xUnite(208);
        yposBtnQuit = yUnite(64);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(xUnite(1), yUnite(1));

        //Ajouter les propriétés et le sprite de btnQuitClicked
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
            case Screen.LOOT_SCREEN:
                lootScreen();
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

    //Loot screen ----------------------------------------------------------------------------------
    public void lootScreen(){ //Gérer l'achat et l'ouverture des lootboxes
        //Gère les inputs de l'écran des loots
        lootScreenInputs();
        //Gère les l'affichage de l'écran des loots
        lootScreenDisp();
    }

    // DISPLAY =====================================================================================
	// Screens -------------------------------------------------------------------------------------
	public void gameScreenDisp(){

        if (!cliqueBtnPause){
            btnPause.setPosition(xposBtnPause, yposBtnPlay);
            btnPause.draw(batch);
        }
        else
        {
            btnPauseClicked.setPosition(xposBtnPause, yposBtnPlay);
            btnPauseClicked.draw(batch);
        }

        font.draw(batch, "GAME STARTED", xUnite(240), yUnite(160));

	}

	public void mainMenuDisp(){

        // bouton 1
        if(!cliqueBtnPlay)
        {
            btnPlay.setPosition(xposBtnPlay, yposBtnPlay);// fixer la position
            btnPlay.draw(batch);                          // puis le dessiner
        }else
        {
            btnPlayClicked.setPosition(xposBtnPlay, yposBtnPlay);
            btnPlayClicked.draw(batch);
        }

        // bouton 2
        if(!cliqueBtnCollection)
        {
            btnCollection.setPosition(xposBtnCollection, yposBtnCollection);// fixer la position
            btnCollection.draw(batch);                          // puis le dessiner
        }else
        {
            btnCollectionClicked.setPosition(xposBtnCollection, yposBtnCollection);
            btnCollectionClicked.draw(batch);
        }

        // bouton 3
        if(!cliqueBtnLeaderboards)
        {
            btnLeaderboards.setPosition(xposBtnLeaderboards, yposBtnLeaderboards);// fixer la position
            btnLeaderboards.draw(batch);                          // puis le dessiner
        }else
        {
            btnCollectionClicked.setPosition(xposBtnLeaderboards, yposBtnLeaderboards);
            btnCollectionClicked.draw(batch);
        }

	}

    public void gamePauseDisp(){

        if (!cliqueBtnPause){
            btnPause.setPosition(xposBtnPause, yposBtnPlay);
            btnPause.draw(batch);
        }
        else
        {
            btnPauseClicked.setPosition(xposBtnPause, yposBtnPlay);
            btnPauseClicked.draw(batch);
        }

        if (!cliqueBtnQuit){
            btnQuit.setPosition(xposBtnQuit, yposBtnQuit);
            btnQuit.draw(batch);
        }
        else
        {

        }




        font.draw(batch, "PAUSE SCREEN", xUnite(240), yUnite(160));
    }

    public void gameEndDisp(){

    }

    public void lootScreenDisp(){
        font.draw(batch, "OPEN YOUR LOOTS", xUnite(240), yUnite(160));
    }

    // INPUTS ======================================================================================
    // Screens -------------------------------------------------------------------------------------
    public void gameScreenInput(){

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

                if (screenX > xUnite(448) && screenY < yUnite(288))
                    cliqueBtnPause = true;

                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {

                if (screenX > xUnite(448) && screenY < yUnite(288))
                    appState = Screen.GAME_PAUSE;

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

    public void mainMenuInput(){

        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean touchUp(int x, int y, int pointer, int bouton) {
                if (x > xUnite(180) && x < xUnite(300) && y < yUnite(88) && y > yUnite(40)) {
                    // le bouton 1 (startGame) a été cliqué
                    appState = Screen.GAME_SCREEN;
                }
                if (x > xUnite(180) && x < xUnite(300) && y > yUnite(115) && y < yUnite(160)) {
                    // le bouton 2 (Loot) a été cliqué
                    appState = Screen.LOOT_SCREEN;
                }

                if (x > xUnite(180) && x < xUnite(300) && y > yUnite(190) && y < yUnite(235)) {
                    // le bouton 3 (Bonus) a été cliqué
                    page = 3;
                }
                if (x > xUnite(0) && x < xUnite(64) && y > yUnite(0) && y < yUnite(64)) {
                    // le bouton retour a été cliqué
                    page = 0;
                }

                cliqueBtnPlay   = false;
                cliqueBtnCollection = false;
                cliqueBtnLeaderboards = false;

                return false;
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int bouton) {

                if(x>xUnite(180) && x < xUnite(300) && y>yUnite(40) && y<yUnite(88) )
                {
                    cliqueBtnPlay  =true;
                }
                if(x>xUnite(180) && x < xUnite(300) && y>yUnite(115) && y<yUnite(160))
                {
                    cliqueBtnCollection=true;
                }
                if(x>xUnite(180) && x < xUnite(300) && y>yUnite(190) && y<yUnite(235))
                {
                    cliqueBtnLeaderboards=true;
                }
                return false;
            }

            @Override
            public boolean touchDragged(int arg0, int arg1, int arg2) {
                return false;
            }

            @Override
            public boolean scrolled(int arg0) {
                return false;
            }

            @Override
            public boolean mouseMoved(int arg0, int arg1) {
                return false;
            }

            @Override
            public boolean keyUp(int arg0) {
                return false;
            }

            @Override
            public boolean keyTyped(char arg0) {
                return false;
            }

            @Override
            public boolean keyDown(int arg0) {
                return false;
            }
        });

    }

    public void gamePauseInput(){

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

                if (screenX > xUnite(448) && screenY < yUnite(288))
                    cliqueBtnPause = false;
                if (screenX > xUnite(208) && screenX < xUnite(272) && screenY < yUnite(288) && screenY > yUnite(224))
                    cliqueBtnQuit = false;

                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {

                if (screenX > xUnite(448) && screenY < yUnite(288))
                    appState = Screen.GAME_SCREEN;

                if (screenX > xUnite(208) && screenX < xUnite(272) && screenY < yUnite(256) && screenY > yUnite(192))
                    appState = Screen.MAIN_MENU;


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

    public void gameEndInput(){

    }

    public void lootScreenInputs(){

    }

    // DESTRUCTION =================================================================================
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
