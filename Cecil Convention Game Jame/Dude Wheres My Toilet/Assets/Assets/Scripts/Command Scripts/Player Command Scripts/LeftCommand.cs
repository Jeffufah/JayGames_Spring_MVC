using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class LeftCommand : CommandBase
{
    public LeftCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "MoveLeft";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        PlayerModifiers modifier = obj.GetComponent<PlayerModifiers>();
        obj.transform.Translate(Vector3.left * modifier.moveSpeed * Time.deltaTime);
    }
}
