using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class HealthPickupCommand : CommandBase
{
    public HealthPickupCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "HealthPickup";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerInventory>().addHealthPack(true);
    }
}