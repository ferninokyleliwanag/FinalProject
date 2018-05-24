public class GAMESTATES {

    public static boolean MENU = false;
    public static boolean PLAY = false;
    public static boolean PAUSE = false;
    public static boolean END = false;
    public static int SCORE = 0;

    public static boolean isMENU() {
        return MENU;
    }

    public static void setMENU(boolean MENU) {
        GAMESTATES.MENU = MENU;
    }

    public static boolean isPLAY() {
        return PLAY;
    }

    public static void setPLAY(boolean PLAY) {
        GAMESTATES.PLAY = PLAY;
    }

    public static boolean isPAUSE() {
        return PAUSE;
    }

    public static void setPAUSE(boolean PAUSE) {
        GAMESTATES.PAUSE = PAUSE;
    }

    public static boolean isEND() {
        return END;
    }

    public static void setEND(boolean END) {
        GAMESTATES.END = END;
    }

    public static int getSCORE() {
        return SCORE;
    }

    public static void setSCORE(int SCORE) {
        GAMESTATES.SCORE = SCORE;
    }

    public static void gameLaunch() {
        setMENU(true);
        setPLAY(false);
        setPAUSE(false);
        setEND(false);
        setSCORE(0);
    }

    public static void startGame() {
        setMENU(false);
        setPLAY(true);
        setPAUSE(false);
        setEND(false);
        setSCORE(0);
    }

    public static void pauseGame() {
        setPLAY(false);
        setPAUSE(true);
    }

    public static void resumeGame() {
        setPLAY(true);
        setPAUSE(false);
    }

    public static void endGame() {
        setMENU(false);
        setPLAY(false);
        setPAUSE(false);
        setEND(true);
    }

}
