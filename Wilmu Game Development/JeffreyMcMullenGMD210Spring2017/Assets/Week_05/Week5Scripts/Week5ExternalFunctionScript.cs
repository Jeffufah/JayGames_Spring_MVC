using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week5ExternalFunctionScript : MonoBehaviour
{
    public string RespondToHello(string Word)
    {
        if (Word == "Hi")
        {
            string response = "What's up?";
            return response;
        }

        else
        {
            string response = "Be polite.";
            return response;
        }
    }
}
