using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnPointConstruct : MonoBehaviour
{
    private void Start()
    {
        SpawnPointCollection.addSpawnPoint(gameObject.transform);      
    }
}
