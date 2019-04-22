using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class CommandBase
{
    protected int entityId;
    protected string commandType;

    public CommandBase()
    {

    }

    virtual public void ExecuteCommand() {}

    public int getEntityId()
    {
        return entityId;
    }

    public string getCommandType()
    {
        return commandType;
    }
}
