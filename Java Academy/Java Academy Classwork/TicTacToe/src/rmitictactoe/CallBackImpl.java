/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitictactoe;

import java.rmi.*;
import java.rmi.server.*;
//netstat-ano
public class CallBackImpl extends UnicastRemoteObject
        implements CallBack
{
    // The client will be called by the server through callback

    private TicTacToeClientRMI thisClient;

    /**
     * Constructor
     */
    public CallBackImpl(Object client) throws RemoteException
    {
        thisClient = (TicTacToeClientRMI) client;
    }

    /**
     * The server notifies the client for taking a turn
     */
    public void takeTurn(boolean turn) throws RemoteException
    {
        thisClient.setMyTurn(turn);
    }

    /**
     * The server sends a message to be displayed by the client
     */
    public void notify(String message) throws RemoteException
    {
        thisClient.setMessage(message);
    }

    /**
     * The server notifies a client of the other player's move
     */
    public void mark(int row, int column, char token)
            throws RemoteException
    {
        thisClient.mark(row, column, token);
    }
    
    public void quit()
    {
        thisClient.quit();
    }
}
