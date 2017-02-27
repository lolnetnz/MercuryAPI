/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.mercuryapi.api.lolcon.request;

/**
 *
 * @author James
 */
public class AddTempCommandRequest {

    private final String playerName;
    private final String packageName;

    public AddTempCommandRequest(String playerName, String packageName) {
        this.playerName = playerName;
        this.packageName = packageName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPackageName() {
        return packageName;
    }

}
