using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class BackwardCommand : CommandBase
{
    public BackwardCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "MoveBackward";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        PlayerModifiers modifier = obj.GetComponent<PlayerModifiers>();
        obj.transform.Translate(Vector3.back * modifier.moveSpeed * Time.deltaTime);
    }
}