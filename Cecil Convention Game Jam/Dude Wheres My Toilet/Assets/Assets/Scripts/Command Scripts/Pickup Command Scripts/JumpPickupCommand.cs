using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class JumpPickupCommand : CommandBase
{
    public JumpPickupCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "JumpPickup";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerInventory>().addJumpBoost(true);
    }
}