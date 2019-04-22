using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class RightCommand : CommandBase
{
    public RightCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "MoveRight";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        PlayerModifiers modifier = obj.GetComponent<PlayerModifiers>();
        obj.transform.Translate(Vector3.right * modifier.moveSpeed * Time.deltaTime);
    }
}
