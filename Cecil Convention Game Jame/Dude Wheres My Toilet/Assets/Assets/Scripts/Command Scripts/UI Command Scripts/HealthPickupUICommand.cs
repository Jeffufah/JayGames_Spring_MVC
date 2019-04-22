using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Even though this says HealthPickupUICommand, it's for all of the pickups at this point.
public class HealthPickupUICommand : CommandBase
{
    int interactingItem;
    bool isInteractingWith;
    public HealthPickupUICommand(int entityId, int interactingItem, bool isInteractingWith)
    {
        this.entityId = entityId;
        this.interactingItem = interactingItem;
        this.isInteractingWith = isInteractingWith;
        commandType = "HealthPickupUI";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        switch (interactingItem)
        {
            case 1:
                obj.GetComponent<HUDIconScripts>().SetIcon1Enabled(isInteractingWith);
                break;
            case 2:
                obj = EntityManager.getEntity(entityId);
                obj.GetComponent<HUDIconScripts>().SetIcon2Enabled(isInteractingWith);
                break;
            case 3:
                obj = EntityManager.getEntity(entityId);
                obj.GetComponent<HUDIconScripts>().SetIcon3Enabled(isInteractingWith);
                break;
        }
    }
}
