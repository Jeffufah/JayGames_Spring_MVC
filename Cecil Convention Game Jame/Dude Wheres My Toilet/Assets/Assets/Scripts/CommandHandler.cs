using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

public class CommandHandler : MonoBehaviour
{
    CommandHub cmdHub;
    Queue<CommandBase> incomingCommands;

    // Start is called before the first frame update
    void Start()
    {
        cmdHub = AccessGameManager.getGameManager().GetComponent<CommandHub>();
        incomingCommands = new Queue<CommandBase>();
    }

    // Update is called once per frame
    void Update()
    {
        while (incomingCommands.Count != 0)
        {
            CommandBase currentCommand = incomingCommands.Dequeue();

            // Execute command appropriately.
            currentCommand.ExecuteCommand();
        }
    }

    // Used for receiving commands from the hub
    public void PassCommand(CommandBase command)
    {
        incomingCommands.Enqueue(command);
    }

    // Used for sending commands to the hub
    public void SendCommand(CommandBase command)
    {
        cmdHub.PassCommand(command);
    }
}
