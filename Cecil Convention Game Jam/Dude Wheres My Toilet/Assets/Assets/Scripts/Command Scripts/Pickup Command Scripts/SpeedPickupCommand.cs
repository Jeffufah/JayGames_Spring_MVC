using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class SpeedPickupCommand : CommandBase
{
    public SpeedPickupCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "SpeedPickup";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerInventory>().addSpeedBoost(true);
    }
}