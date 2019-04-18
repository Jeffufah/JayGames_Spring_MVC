/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitictactoe;

import java.rmi.*;

public interface TicTacToeInterface extends Remote
{
    /**
     * Connect to the TicTacToe server and return the token. If the returned
     * token is ' ', the client is not connected to the server
     * @param client
     * @return 
     * @throws java.rmi.RemoteException
     */
    public char connect(CallBack client) throws RemoteException;

    /**
     * A client invokes this method to notify the server of its move
     * @param row
     * @param column
     * @param token
     * @throws java.rmi.RemoteException
     */
    public void myMove(int row, int column, char token)
            throws RemoteException;
}
