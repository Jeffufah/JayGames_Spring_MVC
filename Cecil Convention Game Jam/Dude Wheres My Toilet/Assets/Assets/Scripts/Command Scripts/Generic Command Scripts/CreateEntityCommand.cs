using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class CreateEntityCommand : CommandBase
{
    private GameObject objPrefab;
    private Transform spawnTransform;

    public CreateEntityCommand(int entityId, GameObject objPrefab, Transform spawnTransform)
    {
        this.entityId = entityId;
        commandType = "CreateEntity";
        this.objPrefab = objPrefab;
        this.spawnTransform = spawnTransform;
    }

    public override void ExecuteCommand()
    {
        GameObject obj = MonoBehaviour.Instantiate(objPrefab, spawnTransform.position, spawnTransform.rotation);
    }
}