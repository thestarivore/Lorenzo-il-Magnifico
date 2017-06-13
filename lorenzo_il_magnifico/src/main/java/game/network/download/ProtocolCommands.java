package game.network.download;

/**
 * Created by Eduard Chirica on 6/13/17.
 */
public enum ProtocolCommands {
    SHOW_WELCOME_MESSAGE ("WELCOME_CMD"),
    ASK_FOR_LOGIN ("ASK_LOGIN_CMD"),

    ;//Ecc

    private String command;

    /**
     * Basic Constructor
     * @param cmd
     */
    ProtocolCommands(String cmd) {
        this.command = cmd;
    }

    /**
     * Get String of the command which will be sent over the com
     * @return a string of the command
     */
    public String getCommand(){
        return command;
    }

}
