using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class DestroyEntityCommand : CommandBase
{
    public DestroyEntityCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "DestroyEntity";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        MonoBehaviour.Destroy(obj);
    }
}