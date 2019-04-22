using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class CloseDoorCommand : CommandBase
{
    public CloseDoorCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "CloseDoor";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.transform.position = new Vector3(obj.transform.position.x, obj.transform.position.y - 2, obj.transform.position.z);
    }
}
