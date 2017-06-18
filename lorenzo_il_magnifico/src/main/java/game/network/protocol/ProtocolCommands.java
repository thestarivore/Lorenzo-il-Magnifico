package game.network.protocol;

/**
 * Created by Eduard Chirica on 6/13/17.
 */
public enum ProtocolCommands {
    //Client Side
    PLAYER_IDENTIFIACTION ("PLAYER_IDENTIFICATION_CMD[%s,%d]"),
    COLOR_SELECTION ("COLOR_SELECTION[%s]"),

    //Server Side
    ACK ("ACK"),
    SELECT_COLOR ("SELECT_COLOR[%s,%s,%s,%s]"),

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

    /**
     * Control whether the received command is this command
     * @param cmdReceived
     * @return "true" if is this command, "false" otherwise
     */
    public boolean isThisCmd(String cmdReceived){
        //Calculate the index of where the command prefix stops
        int prefixIndex = command.indexOf("[");

        //If cmd has no arguments, prefixIndex will be -1
        if(prefixIndex == -1)
            prefixIndex = command.length();

        //Get the String of the prefix
        String cmdPrefix = command.substring(0,prefixIndex);

        //Control if the received cmd is in fact this command
        if(cmdReceived.contains(cmdPrefix))
            return true;
        else
            return false;
    }

    /**
     * Get String of the command which will be sent over the com
     * and format all arguments passed.
     * @param args arguments
     * @return a string of the command
     */
    public String getCommand(Object... args){
        return String.format(command, args);
    }


    /**
     * Get data from received command.
     * Gets all the arguments received in a cmd,
     * and returns them in a String array.
     * @param cmd received command
     * @return an String array of the received arguments
     */
    public static String[] getDataFromCommand(String cmd){
        String data;

        //Find index of where data starts
        int indexStart = cmd.indexOf("[")+1;

        //Find index of where data stops
        int indexStop = cmd.indexOf("]");

        //Calculate data string and return it
        data = cmd.substring(indexStart, indexStop);

        //Get all arguments
        String[] tokens = data.split(",");
        return tokens;
    }
}
