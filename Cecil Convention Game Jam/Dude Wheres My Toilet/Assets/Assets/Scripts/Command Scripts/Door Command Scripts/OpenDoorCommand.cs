using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class OpenDoorCommand : CommandBase
{
    public OpenDoorCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "OpenDoor";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.transform.position = new Vector3(obj.transform.position.x, obj.transform.position.y + 2, obj.transform.position.z);
    }
}
