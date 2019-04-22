using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class SprintCommand : CommandBase
{
    bool isSprinting;

    public SprintCommand(int entityId, bool isSprinting)
    {
        this.entityId = entityId;
        this.isSprinting = isSprinting;
        commandType = "Sprint";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerModifiers>().isSprinting = isSprinting;
    }
}
