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

	public Sprite btn1;
	public Sprite btn2;
	public Sprite btn3;

    public Sprite btn1clicked;
    public Sprite btn2clicked;
    public Sprite btn3clicked;

    public float largeur_ecran;
    public float hauteur_ecran;

	public float xposBtn1;
	public float yposBtn1;
    public float xposBtn2;
    public float yposBtn2;
    public float xposBtn3;
    public float yposBtn3;

    public boolean cliqueBouton1;
    public boolean cliqueBouton2;
    public boolean cliqueBouton3;

    public int page;



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

        // Obtenir la taille de l'écran actuelle
        largeur_ecran = Gdx.graphics.getWidth();
        hauteur_ecran = Gdx.graphics.getHeight();


		btn1 = new Sprite(new Texture(Gdx.files.internal("bouton.png")));

		btn2 = new Sprite(new Texture(Gdx.files.internal("bouton.png")));

		btn3 = new Sprite(new Texture(Gdx.files.internal("bouton.png")));


        btn1clicked = new Sprite(new Texture(Gdx.files.internal("boutonclique.png")));

        btn2clicked = new Sprite(new Texture(Gdx.files.internal("boutonclique.png")));

        btn3clicked = new Sprite(new Texture(Gdx.files.internal("boutonclique.png")));



        btn1.setSize(xUnite(128), yUnite(64));

		btn2.setSize(xUnite(128), yUnite(64));

		btn3.setSize(xUnite(128), yUnite(64));

        btn1clicked.setSize(xUnite(128), yUnite(64));

        btn2clicked.setSize(xUnite(128), yUnite(64));

        btn3clicked.setSize(xUnite(128), yUnite(64));


        xposBtn1 = xUnite(176);
		yposBtn1 = yUnite(223);

        xposBtn2 = xUnite(176);
        yposBtn2 = yUnite(150);

        xposBtn3 = xUnite(176);
        yposBtn3 = yUnite(74);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(xUnite(1), yUnite(1));


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

        font.draw(batch, "GAME STARTED", xUnite(240), yUnite(160));

	}

	public void mainMenuDisp(){

        // bouton 1
        if(!cliqueBouton1)
        {
            btn1.setPosition(xposBtn1, yposBtn1);// fixer la position
            btn1.draw(batch);                          // puis le dessiner
        }else
        {
            btn1clicked.setPosition(xposBtn1, yposBtn1);
            btn1clicked.draw(batch);
        }

        // bouton 2
        if(!cliqueBouton2)
        {
            btn2.setPosition(xposBtn2, yposBtn2);// fixer la position
            btn2.draw(batch);                          // puis le dessiner
        }else
        {
            btn2clicked.setPosition(xposBtn2, yposBtn2);
            btn2clicked.draw(batch);
        }

        // bouton 3
        if(!cliqueBouton3)
        {
            btn3.setPosition(xposBtn3, yposBtn3);// fixer la position
            btn3.draw(batch);                          // puis le dessiner
        }else
        {
            btn2clicked.setPosition(xposBtn3, yposBtn3);
            btn2clicked.draw(batch);
        }

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
            public boolean touchUp(int x, int y, int pointer, int bouton) {
                if (x > xUnite(180) && x < xUnite(300) && y < yUnite(88) && y > yUnite(40)) {
                    // le bouton 1 (startGame) a été cliqué
                    appState = Screen.GAME_SCREEN;
                }
                if (x > xUnite(180) && x < xUnite(300) && y > yUnite(115) && y < yUnite(160)) {
                    // le bouton 2 (Options) a été cliqué
                    page = 2;
                }

                if (x > xUnite(180) && x < xUnite(300) && y > yUnite(190) && y < yUnite(235)) {
                    // le bouton 3 (Bonus) a été cliqué
                    page = 3;
                }
                if (x > xUnite(0) && x < xUnite(64) && y > yUnite(0) && y < yUnite(64)) {
                    // le bouton retour a été cliqué
                    page = 0;
                }

                cliqueBouton1 = false;
                cliqueBouton2 = false;
                cliqueBouton3 = false;

                return false;
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int bouton) {

                if(x>xUnite(180) && x < xUnite(300) && y>yUnite(40) && y<yUnite(88) )
                {
                    cliqueBouton1=true;
                }
                if(x>xUnite(180) && x < xUnite(300) && y>yUnite(115) && y<yUnite(160))
                {
                    cliqueBouton2=true;
                }
                if(x>xUnite(180) && x < xUnite(300) && y>yUnite(190) && y<yUnite(235))
                {
                    cliqueBouton3=true;
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
