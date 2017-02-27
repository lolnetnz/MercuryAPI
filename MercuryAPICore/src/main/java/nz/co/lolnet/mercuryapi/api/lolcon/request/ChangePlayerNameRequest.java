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
public class ChangePlayerNameRequest {

    private final String playerName;
    private final String playerUUID;

    public ChangePlayerNameRequest(String playerName, String playerUUID) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

}
