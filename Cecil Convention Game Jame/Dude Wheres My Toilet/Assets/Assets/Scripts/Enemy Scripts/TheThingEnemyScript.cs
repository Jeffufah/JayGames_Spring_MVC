using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TheThingEnemyScript : MonoBehaviour
{
    private CommandHandler commandHandler;

    private float respawnTimer;

    private void Start()
    {
        respawnTimer = 0;
    }

    private void Update()
    {
        respawnTimer += Time.deltaTime;

        if (respawnTimer >= 2)
        {
            respawnTimer = 0;

            Transform tForm = SpawnPointCollection.getRandomSpawnTransform();

            gameObject.transform.position = tForm.position;
        }
    }

    void OnTriggerEnter(Collider theCollision)
    {
        try
        {
            if (theCollision.gameObject.GetComponent<EntityIdentity>().entityName == "Player")
            {
                int colEntityId = theCollision.gameObject.GetComponent<EntityIdentity>().getEntityId();
                commandHandler = theCollision.gameObject.GetComponent<CommandHandler>();
                TakeDamageCommand takeDamageCommand = new TakeDamageCommand(colEntityId, 15f);
                commandHandler.SendCommand(takeDamageCommand);
            }
            else
            {
                Debug.Log("Not player");
            }
        }
        catch
        {
            Debug.Log("Doesn't have EntityIdentity");
        }

    }
}