using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CommandHub : MonoBehaviour
{
    Queue<CommandHandler> cmdHandlers;
    Queue<CommandBase> incomingCommands;

    // Start is called before the first frame update
    void Start()
    {
        incomingCommands = new Queue<CommandBase>();
        cmdHandlers = new Queue<CommandHandler>();
    }

    // Update is called once per frame
    void Update()
    {
        while (incomingCommands.Count != 0)
        {
            CommandBase currentCommand = incomingCommands.Dequeue();
            GameObject entity = EntityManager.getEntity(currentCommand.getEntityId());
            // Route command to appropriate listener
            entity.GetComponent<CommandHandler>().PassCommand(currentCommand);
        }
    }

    // Used to register the different Command Handlers.
    public void RegisterListener(CommandHandler cmdHandler)
    {
        cmdHandlers.Enqueue(cmdHandler);
    }

    // Used for receiving commands from command handlers
    public void PassCommand(CommandBase command)
    {
        incomingCommands.Enqueue(command);
    }
}
